****************************************************
Abir Abbas    				     0955448
CIS2430 - Assignment #3
2017-11-28                        abbasa@uoguelph.ca
****************************************************
General Problem : 
The purpose of this program is to add a functional GUI for the previously made assignment 2,
The GUI must follow a certain format, and must handle exceptions according to each invidual issue that may arise

The program will feature :
Previous Features : 
- Buy stock option which either adds prexisting stocks or creates a new instance of it
- Sell stock option, sells existing at a price and calculates gains/losses
- Update feature to update prices and calculate gains/losses
-Search Feature, to find a specific stock through, symbol, keyword(s), price ranges
- getGain feature to return the total gains 
- And lastly to simply exit the program  
New Features : 
- A simple interface containing all panels (Buy,sell,etc)
- Custome exception handling for each input
- File I/O from command line arguments and on close will save all information!

************
Compilation
************
//go into package and type this
On command line : javac *.java

On Netbeans : Hit the build button

***********************
Running the program(s)
***********************
//inside the package
On command line : java guiAssignment3.java "fileName.txt"

On Netbeans : Open package on net beans and hit debugger
Requires arguments for filename, else program will exit!

*****************
Known Limitations
*****************
Must contain a fileName, otherwise program will exit
Must enter proper information

*****************
Test Plan
*****************

Conditions :

openFile :
1. No fileName was entered
	exit program and ask user to enter fileName
2. fileName entered does not exist
	create file and save accordingly
3. fileName entered points to an empty file
	read nothing, only write when user is done with the program!
4. fileName entered has information
	read information accordingly!

writeFile :
1. file does not exist
	create the file and write to it
2. file exists but already contains data
	simply overwrite the file to avoid duplicate entries
3. file exists with no data
	just write to the file

buySomething :
1. user enters invalid inputs
	prommpt user to input valid data
2. user enters nothing
	prompt user to input valid data

sellSomething :
1. user tries to sell more than what they have
	let user know he/she may not sell more than what they own
2. user sells everything
	delete the whole investment
3. user sells partial goods
	only take out what the user specifies
4. user inputs invalid data
	prompt user to input proper data
5. user inputs nothing
	prompt user to input proper data

update :
1. user enters something that isn't an integer
	prompt user to enter an integer
2. user enters nothing
	prompt user to enter an integer

search : NOTE (everything stated will use .toLowerCase() to avoid any capitalization issues)
1. user enters nothing
	display all instances of the arrayList
2. user only enters symbol
	display all iterations that match the symbol exactly 
3. user only enters keyWords
	display all iterations that match all keyWords
4. user only enters priceRange
	display all iterations that fall into the priceRange
5. user enters symbol and keyword
	find all iterations that match all keyWords then only output the one that matches exactly with the symbol
6. user enters keyWord and price range 
	display all iterations that match all keywords and falls into price range
7. user enters symbol and price range
	display all iteration that matches symbol exactly and falls into price range
8. user enters symbol, keyword and price range
	display all iterations that match all keywords and exactly matches symbol and also falls into price range

getGain :
1. No current gains 
	display 0s
2. Negative gains
	Display the negative value (means a loss)
3. Nothing exists in the list
	display 0 because no gains

Test Cases :
1. User tries to entering invalid inputs into a String or an integer 
	Solution : Program will not add a Investment without proper data with proper formatting
2. User tries to go out of bounds in Update Menu
	Solution : Disable buttons once user is about to go out of bounds
3. User tries to Negative values
	Solution : always check if values are greater than 0 and if they aren't prompt user to enter proper data
4. tries to search/sell/getGain from an empty list
	Solution : always check if list is empty before proceding to any function, if let the user know there is nothing in the list
5. Entering Nothing in options that require a user input
	Solution : keep prompting user to enter something and while 	
	loop till String is no longer empty, if String is empty
	return invalid input!
6. Trying to sell at a lower price, then intial, which returns negative profits
	Solution : Negative profits is fine, it just means user 	
	incurred a loss so its fair game!
7. Try inputting Numbers into something that requires a string
	Solution : Create a function that checks if user input is a 	
	number, if it is it returns true, if not it returns false 	
	and tells the user that he/she entered an invalid input!
8. Enter in Nulls into functions
	Solution : Always check for Nulls at the begining of every 		
	function, if (something == NULL) { exit the program }, this 	
	will assure that the program does not have any Out of bounds 	
	errors, and such

*****************
Improvements
*****************
1. Create a more elegant layout, using GridLayout stretches out a lot of things, having my own custom layout would make things look better!

2. Improve my exception handler, to return exact data and what went wrong and give users example of proper data

2. Format some outputs in a better fashion, a lot of my outputs look very rushed and plain, if i had time i would make sure i could format it better for better user understability.

3. Reduce the amount of repeated code via creating functions for example, try catching statements every single time (when i can just send the params into a function and check if try/catch fails)

4. Although the assignment states to use sequential search, it does bother me that it is a very inefficient  method of searching, if I had better knowledge and understanding and more time 
I would definitely use a better search option, and also would use functions like insertSorted to make iterating through the list a lot easier and quicker!

5. Upgrade the hashFunction (currently just reHashes the whole list)

6. The symbol search method still implements sequential search, which from according to the assignment description is what was expected, but if i had time i would have
used the HashMap in the search method with symbol aswell!



