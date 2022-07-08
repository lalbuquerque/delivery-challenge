# Problem statement

* Design a simple app to show the upcoming orders for a user in a list.
* Show the delivery date and time for a selected delivery in the action bar/tool bar on top of screen.

# API Details
* Host: https://demo2017106.mockable.io
* GET api/v1/store/deliveries/upcoming
    - ERROR CODES - 401 for invalid user, 404 for invalid request
    - QUERY PARAMS: hub_id=2, ak=jSsxdbg89YWiAQFXFmLL

# Expectations:
* The code for app logic should preferably be 100% Kotlin.
* The problem statement is designed to simulate regular work day feature building; completing each and every step is NOT a priority.
* Focus on clean code while accounting for development speed with quality. Ex: If you are comfortable using Dagger or any DI library, go for it! However, that does not gain additional points.
* Unit tests for some business logic is encouraged. Ex: Complete Step 1 and write some unit tests.
* Please feel free to choose whatever android design pattern, UI pattern and any dependencies to accomplish this task (MVVM, MVP, Coroutines, Flows anything works!)
* Please approach this as your regular work day request; commit as often as you want if that is your preferred style.
* Note - completing all/any of the steps mentioned below is not a priority. Step 1 and Step 2 are as mentioned in the problem statement, and just think of Step 3 and above as additional challenges if time permits.
* Note - the project skeleton files (MainActivity.kt, FirstFragment.kt etc) are just placeholders in this repo. Please feel free to modify any repo files as required.
* Updating the README with some salient points on app architecture is encouraged (not required)

# Steps

## Step 1
* Main app screen should show a list of upcoming deliveries (as returned via the endpoint api/v1/store/deliveries/upcoming)
* Each list item should show the delivery day, delivery date and delivery time range Ex: Mon May 2 4:00 PM - 6:00 PM
* If the delivery state is `instant_pending`, a label "pending" should be displayed in the list item.
* Tapping on back button should exit to device home screen

## Step 2
* The app tool bar/action bar on top should display details of current selected delivery.
* Toolbar UI should show the delivery day, delivery date and delivery time range Ex: Mon May 2 4:00 PM - 6:00 PM
* By default on first app start, the first `instant_pending` delivery should be the default selected delivery.
* Tapping on an item in list of upcoming deliveries should change toolbar UI to that selected delivery
* The selected delivery should persist on app close. A fresh app open should show the last selected delivery in tool bar.

# Additional challenges

## Step 3
* Each delivery in the list has order items. Show the order item images in each list item.
* Show at the max 4 individual order item images in the list item. If there are more than 4 items in the delivery, add a suffix "and more items" to the images row.

## Step 4
* Tapping on an item in list of upcoming deliveries should open a new view cart screen, for showing all items in that delivery.
* The above endpoint response has `order_items` array for each delivery, which should contain all items in cart for that delivery
* The view cart screen should show this list of order items
* Each list item can show some basic item data (ex: Item name, item price, item quantity and possibly item image)
* Item price would be corresponding to `hub_id=2` under `hub_items` field
* Tapping on back button in this screen should revert back to upcoming deliveries screen

## Step 5
* Tapping on a item in view cart list should open that product detail page in a separate screen.
* The product detail screen can show basic product data - ex: a larger image of the product, product name, `display_size_and_measure` and product price
* Tapping on back button should exit back to view cart page.

## 1 to 3 Steps
<img src="https://user-images.githubusercontent.com/2593625/166195729-c3a3c8d4-3f27-4265-a1aa-d4245351fcd5.gif" width="360" height="640"/>

## 4 and 5 Steps
![farmstead-challenge-2-resize](https://user-images.githubusercontent.com/2593625/166195669-8f991bcc-695a-4698-921c-6fd1d930902d.gif)
