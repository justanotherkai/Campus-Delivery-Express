# Campus Delivery Express

## Program description (from guidelines):
Develop a Java program that handles user orders and deliveries (e.g, Panda Express) from within on-campus users. The program will allow the user to choose from a list of available menus from different restaurants and enter the quantity they wish to purchase. The service charge depends on the type of menu/order and the location of the restaurants (i.e., restaurants further away from campus will be charged higher cost). If the food/menu is not available, the user shall be able to change or cancel their order. The program will finally print a receipt with details of the food ordered, the total price & quantity, the location of the restaurant and the service cost charged. You may also extend your delivery services to other services such as printing/binding service, license renewal and other services needed most by campus students

## Program structure
Program Codes are seperated into several classes:
1. CampusDeliveryExpress
2. Food
3. Services
4. Checkout
5. Display (package: utils)
6. Input (package: utils)

The more multipurpose or general methods are grouped in the utils package so that it can be accessed by classes that needs it in this program or even other future programs.

### Program content
Starts with the main menu:
1. Food
2. Services
3. Exit

Two main branches:
1. Main menu > Food > Restaurant > Menu > Checkout > Main menu
2. Main menu > Service > Location > Options > Checkout > Main menu

## Food
- After choosing food in main menu, user is directed to a page to choose a restaurant they want to order from
- User choose by inputting the number of one of the restaurants/locations displayed
- Then, user is directed to the menu page
- This is where user can look at the different meals available at chosen location
- User is prompted to input a number for which meals they want to add or remove
- After choosing the meal they can add the meal to their order by specifying the quantity or remove their previous order by inputting negative number
- If user are done with their order, they can input q to proceed
- The program then jumps to the checkout section

## Services

## Checkout
