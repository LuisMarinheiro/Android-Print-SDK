# Android Print SDK

The [Kite](http://kite.ly) Android Print SDK makes it easy to add print on demand functionality to your app.

Harness our worldwide print and distribution network. We'll take care of all the tricky printing and postage stuff for you!

To get started, you will need to have a free Kite developer account. Go to [kite.ly](https://developer.psilov.eu) to sign up for free.

## Products

Use print to unlock hidden revenue streams and add value for your users. *In under an hour* you could be using our Android Print SDK to print:

- Magnets
- Polaroid Style Prints
- Square Prints
- Postcards
- A4 (invoices, letters, etc)
- New products being added monthly 

## Features
- Print a wide variety of [products](#products) on demand
- Dynamic control over the pricing of products in you app pricing using our web [Developer Dashboard](https://developer.psilov.eu)
- Revenue & order volume analytics available in the web dashboard
- Review, refund or reprint any order within the web dashboard
- Localized currency support
- Worldwide address search & lookup
- No server infrastructure required. We can handle everything for you from processing payments to printing & postage
- Your branding not ours. You can create your own custom checkout & payment UI or customize ours

## Requirements

* Android API Level 14 - Android 4.0 (ICE_CREAM_SANDWICH)

## Installation
Coming soon :)

## Use Cases

The Kite Android Print SDK supports two primary use cases: **Managed Checkout** and **Custom Checkout**.

### Managed Checkout

The Kite Print SDK includes a robust checkout and payment experience that's proven to convert well with users. It can take care of the entire checkout process for you, no need to spend time building any user interfaces. 

This is the quickest approach to integration and perfect if you don't want to spend a great deal of time building a custom checkout experience.  To use it:

1. [Create a print order](docs/create_print_order.md) representing the product(s) you wish to have printed and posted
2. [Create and start a `CheckoutActivity`](docs/managed_checkout.md) passing it the `PrintOrder` object created in Step 1
3. [Register your payment details](https://developer.psilov.eu/accounts/billing/) with us so that we can pay you when your users place orders


### Custom Checkout
You can build your own UI if you don't want to use or customize the provided checkout and payment experience. You can still use the Kite Print SDK to handle the print order creation and submission: 

1. [Create a print order](docs/create_print_order.md) representing the product(s) you wish to have printed and posted
2. [Set the shipping address](docs/shipping.md) to which the order will be delivered
3. [Take payment from the user](docs/payment.md) for the order and submit it to our servers for printing and posting
4. [Register your payment details](https://developer.psilov.eu/accounts/billing/) with us so that we can pay you when your users place orders

## Credentials & Environments
Your mobile app integration requires different API Key values for each environment: Live and Test (Sandbox).

You can find your Kite Print API credentials under the [Credentials](https://developer.psilov.eu/accounts/credentials/) section of the development dashboard.

### Sandbox

Your Sandbox API Key can be used to submit test print orders to our servers. These orders will not be printed and posted but will allow you to integrate the Print SDK into your app without incurring cost. During development and testing you'll primarily want to be using the sandbox environment to avoid moving real money around.

When you're ready to test the end to end printing and postage process; and before you submit your app to the App Store, you'll need to swap in your live API key.

### Live

Your Live API Key is used to submit print orders to our servers that will be printed and posted to the recipient specified. Live orders cost real money. This cost typically passed on to your end user (although this doesn't have to be the case if you want to cover it yourself). 

Logging in to our [Developer Dashboard](https://developer.psilov.eu) allow's you to dynamically change the end user price i.e. the revenue you want to make on every order. Payment in several currencies is supported so that you can easily localize prices for your users. The dashboard also provides an overview of print order volume and the money you're making.

## Documentation

* These docs in the SDK, which include an overview of usage, step-by-step integration instructions, and sample code
* The sample app included in this SDK
* Source files are thoroughly documented; refer to them as needed for extra details about any given class or parameter
* The [Kite Print API & SDK Developer Docs](https://developer.psilov.eu/docs/1.1/), which cover error codes and optional server-side integration instructions

## License

Kite Android Print SDK is available under the MIT license. See the LICENSE file for more info.

