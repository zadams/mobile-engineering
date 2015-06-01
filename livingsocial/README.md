
## Andres Cano Test Submission Notes:

### Setting Up App:

To set up the app make sure to open in XCode the file named livingsocial.xcworkspace within the folder called livingsocial in the mobile-engineering folder of the fork, this to ensure that the pods that are being part of the project will compile properly at the moment of testing the app.

Once the workspace is open, you can compile the giving project either using the simulator or a testing device, in any case select a device of your preferences in the only scheme available and click the play symbol on the top left area of XCode, or just run the project using the shortcut command-R.

An App named "livingsocial" will appear on the main area of the selected device to run the app. once its open make sure to click Load JSON from the navigation area in order to populate the content and test different features of the app.

### 3rd Party Libraries

The project is currently using Cocoapods which provides a mechanisim to install libraries with no hastle, and AFNetworking to handle some aspects of the comunication with the server to retrieve the JSON content and to asynchronously downloads an image from the specified URL.

More information about AFNetworking is available on [AFNetworking](https://github.com/AFNetworking/AFNetworking)
