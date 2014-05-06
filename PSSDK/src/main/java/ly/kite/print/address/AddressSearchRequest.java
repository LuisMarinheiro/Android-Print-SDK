package ly.kite.print.address;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import ly.kite.print.BaseRequest;
import ly.kite.print.KitePrintSDK;
import ly.kite.print.KitePrintSDKException;

/**
 * Created by deonbotha on 29/01/2014.
 */
public class AddressSearchRequest {

    private BaseRequest searchRequest;

    public void cancelSearch() {
        if (searchRequest != null) {
            searchRequest.cancel();
            searchRequest = null;
        }
    }

    public void search(String query, Country country, AddressSearchRequestListener listener) {
        String queryParams = null;
        try {
            queryParams = String.format("search_term=%s&country_code=%s", URLEncoder.encode(query, "utf-8"), country.getCodeAlpha3());
        } catch (UnsupportedEncodingException e) {
            listener.onError(AddressSearchRequest.this, e);
            return;
        }

        String url = String.format("%s/v1/address/search?%s", KitePrintSDK.getEnvironment().getPrintAPIEndpoint(), queryParams);
        startSearch(url, country, listener);
    }

    public void search(Address address, AddressSearchRequestListener listener) {
        String queryParams = null;
        try {
            if (address.getId() == null) {
                if (address.getCountry().getCodeAlpha3().equals("GBR") && address.getZipOrPostalCode() != null && address.getZipOrPostalCode().length() > 0) {
                    String line1 = address.getLine1() == null ? "" : address.getLine1();
                    queryParams = String.format("postcode=%s&address_line_1=%s&country_code=GBR", URLEncoder.encode(address.getZipOrPostalCode(), "utf-8"), URLEncoder.encode(line1, "utf-8"));
                } else {
                    queryParams = String.format("search_term=%s&country_code=%s", URLEncoder.encode(address.getDisplayAddressWithoutRecipient(), "utf-8"), address.getCountry().getCodeAlpha3());
                }
            } else {
                queryParams = String.format("address_id=%s&country_code=%s", URLEncoder.encode(address.getId(), "utf-8"), address.getCountry().getCodeAlpha3());
            }
        } catch (UnsupportedEncodingException ex) {
            listener.onError(AddressSearchRequest.this, ex);
            return;
        }

        String url = String.format("%s/v1/address/search?%s", KitePrintSDK.getEnvironment().getPrintAPIEndpoint(), queryParams);
        startSearch(url, address.getCountry(), listener);
    }

    private void startSearch(String url, final Country country, final AddressSearchRequestListener listener) {
        searchRequest = new BaseRequest(BaseRequest.HttpMethod.GET, url, null, null);
        searchRequest.start(new BaseRequest.BaseRequestListener() {
            @Override
            public void onSuccess(int httpStatusCode, JSONObject json) {
                searchRequest = null;
                JSONObject error = json.optJSONObject("error");
                JSONArray choices = json.optJSONArray("choices");
                JSONObject unique = json.optJSONObject("unique");
                if (error != null) {
                    String message = error.optString("message");
                    listener.onError(AddressSearchRequest.this, new KitePrintSDKException(message));
                } else if (choices != null) {
                    ArrayList<Address> addrs = new ArrayList<Address>();
                    for (int i = 0; i < choices.length(); ++i) {
                        JSONObject choice = choices.optJSONObject(i);
                        Address a = Address.createPartialAddress(choice.optString("address_id"), choice.optString("display_address"));
                        a.setCountry(country);
                        addrs.add(a);
                    }
                    listener.onMultipleChoices(AddressSearchRequest.this, addrs);
                } else {
                    assert unique != null : "oops this should be the only option left";
                    Address addr = new Address();
                    addr.setLine1(unique.optString("address_line_1"));
                    addr.setLine2(unique.optString("address_line_2"));
                    addr.setCity(unique.optString("city"));
                    addr.setStateOrCounty(unique.optString("county_state"));
                    addr.setZipOrPostalCode(unique.optString("postcode"));
                    addr.setCountry(Country.getInstance(unique.optString("country_code")));
                    listener.onUniqueAddress(AddressSearchRequest.this, addr);
                }
            }

            @Override
            public void onError(Exception ex) {
                searchRequest = null;
                listener.onError(AddressSearchRequest.this, ex);
            }
        });
    }


}
