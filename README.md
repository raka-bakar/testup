# Mobile Coding Challenge - Take Home Task

The purpose of this assignment is to evaluate your coding performance and to understand your attitude towards software development.

## The task

We have an app that displays a catalog of items for sale (Toasts) in a table. At the moment, the app just shows a white screen when launching it. We need you to complete the work and connect existing pieces! 

### Task 1: Connect app to backend

We want to display the list of Toasts using our backend as data source. Finish the `NetworkClient` implementation, connect it to our `ItemsViewController`/`ItemsActivity`.

Once complete, the app should look like this:

<img src="Steps/STEP%201.png" alt="Step 1" width="200"/>

### Task 2: Add “Last Sold” Date and Display currency of price

There is information missing from the table cells: users should see the date when the Toast was last sold and the price should be displayed in the correct currency.

Once complete, the app should look like this:

<img src="Steps/STEP%202.png" alt="Step 2" width="200"/>

Note: The goal is that the date is human-readable. The format visible in the screenshot is just a suggestion, but it doesn't have to match exactly.

### Task 3: Add tests to `NetworkClient`

Write two test cases for our `NetworkClient` class: the happy path and the unhappy path. You may build on top of the existing `NetworkClientTests` if you want to, but you are free to approach the test cases in any way you see fit.

### Task 4: Display images in list

The table cells should display dedicated images - a circle with the ID of the Toast - for each Toast. 
* On iOS: we need you to connect the existing `ImageRepository` class and use it to display the correct images
* On Android: we need you to implement the `setIcon()` method in `ItemsAdapter.ViewHolder`

Once complete, the app should look like this:

<img src="Steps/STEP%204.png" alt="Step 1" width="200"/>

## Advice for the challenge

This app is not expected to be production-ready. The goal is to provide the functionalities listed above. You are free to change the code in any way you deem makes sense, but can also take shortcuts and discuss them with us during the review of your task.
As a rule of thumb: it should not be necessary to spend more than 2 hours to complete all tasks.
