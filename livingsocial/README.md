
## Andres Cano Test Submission Notes:

### Initial Installation and Setup:

To set up the app make sure to open the file named livingsocial.xcworkspace in Xcode inside the folder called livingsocial within the mobile-engineering folder of the fork, this to ensure that the pods (Cocoapods) that are being part of the project will compile properly at the moment of installing the app in the simulator or a device.

Once the workspace is open, compile the livingsocial project either using the simulator or a testing device, to do this, just run the project using the shortcut command-R, or the play icon of the top left area of Xcode.

An App named "livingsocial" will appear on the main area of the selected device or simulator. Open the app, if the content don't load right away because of internet conectivity, make sure to tap the "reload" icon on the top left area of the app, this to populate the content and test different features of the app.

### 3rd Party Libraries

The project is currently using Cocoapods which provides a mechanisim to install libraries with no hastle, I'm using the pod AFNetworking to handle some aspects of the comunication with the server to retrieve the JSON content and to asynchronously downloads an image from the specified URL, also to comunicate the network status.

More information about AFNetworking is available on [AFNetworking](https://github.com/AFNetworking/AFNetworking)

If you have any question, please let me know!

Thanks!

Andres Cano.
