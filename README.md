# Register system for restaurants
1. [Description](#1-description)
2. [Functionality](#2-functionality)
3. [Installation](#3-installation)
4. [Use](#4-use)
5. [Technical information](#5-technical_information)
6. [Contact](#6-contact)

## 1. Description
This project is an overworked version of the requirements for my school assignment. Initially the function that was asked for was only the tab view, but I wanted to try my knowledge and widen it.

The program is a register used for restaurants to facilitate the management of several tables with tabs and payments at once. The user can get a good overview of the tables in the restaurant, active tabs, payment and transactions.


## 2. Functionality
* Overview of all tables
* Creation and managing several active tabs
* Simulating payment
* Overview of saved transactions


## 3. Installation
### Requirements
Built with Java version 21.0.4


### Clone repo
git clone https://github.com/lafftale1999/TableTabKeeper.git


### Run program
Run the “src/mainPackage/App.java” file to run the program.


## 4. Use
### 4.1 The program
#### 4.1.1 ViewTables
When starting the program the user is shown an overview of all tables and if they have any open tabs. The user can choose to click on a table and when doing so the table will change to color yellow when selected and the bottom panel will also change information to match the currently selected table.
While no table is selected the user only have the option to choose “Transactions”
When a table is selected and the table has no active tabs the user will be prompted to “Create Tab”, else the button will say “Open Tab”.


#### 4.1.2 ViewTab
After clicking “Create Tab” or “Open Tab” the user will be navigated to the ViewTab for the current table. Here the user can:
* Add items to the tab by navigating the buttons in the big panel. When an item is clicked it will be added to the information box in the bottom panel, where the user can increase / decrease the amount and then add the item.
* Remove items by clicking the “-” button next to the items in the side panel. This will only remove 1 amount of the item at the time and then remove it from the tab when the amount hits 0.
* Pay / Close tab depending if the tab contains any items (Pay Tab) or not (Close Tab).
* Go back to ViewTables.


#### 4.1.3 ViewPayment
After clicking “Pay Tab” the user is shown the payment view. Here the user first has to choose a payment method in the main panel before paying the tab. The chosen method will be highlighted in blue and the payment info shown in the bottom panel. Here the user can:
* Pay tab which creates a new transaction and saves it to our transactions list. After the transaction is created the user is navigated back to ViewTables.
* Go Back which cancels the payment.


#### 4.1.4 ViewTransactions
By clicking “Transactions” in ViewTables the user is shown all the saved transactions from the transactions.txt file and the ones created since starting the program.


### 4.2 Updating menu items
By navigating the file structure to “src/files/foodMenu” the user will find the .txt files with all menu items. Here we can change, delete or add items that will be added into the program during runtime. Important that:
* The order is as follows: <MenuItem>,<Price>,<TaxGroup>.
* The attributes are separated by commas “,”.
* <TaxGroup> only supports float numbers 0.12 and 0.25 at the moment (Swedish tax brackets for food, beverages and alcohol).

##### Important:Changes to the menu done while running the program will only be made AFTER the program has restarted.


## 5. Technical information
The program is written entirely in java and uses java swing for GUI.


### 5.1 Map and file structure
#### 5.1.1 src/classes
* MenuItems.java for handling the creation of new products.
* OpenTab.java for handling the active tabs
* Payment.java for creating payments
* ProductCategory.java for creating BorderButton and MenuItems lists for HashMap in MenuItems.java
* Table.java for handling table content and icons.
* Transaction reads all saved transactions, creates transactions and writes saved transactions back to transactions.txt


#### 5.1.2 src/files
* foodMenu/* contains all menu items.
* transactions.txt contains all saved transactions


#### 5.1.3 src/GUIs/*
* buttons contains all the buttons used in several places of the program.
* frames for the frames opened when and during the program runs.
* panels contain the methods for each panel to consolidate and draw information, as well as creating buttons for the layers.


#### 6.1.4 src/images/*
* All images used in the program


#### 6.1.5 src/LayersView/*
* ViewPayment handles the coordination of all panels and buttons in the payment view.
* ViewTab handles the coordination of all panels and buttons in the tab view.
* ViewTables handles the coordination of all panels and buttons in the tables view.


#### 6.1.6 src/mainPackage/*
* App.java is where the main method lies to run the program.


## 6. Contact
If you have any questions or want to give me some feedback on this project, please reach out to me on LinkedIn: https://www.linkedin.com/in/carlbroman/
