****************************************************
Abir Abbas    				     0955448
CIS2430 - Assignment #2
2017-11-10                        abbasa@uoguelph.ca
****************************************************
General Problem : 
The purpose of this program is to give a user the nice and simple program which allows him/her to keep track of his/her investments.

The program will feature :
- Buy stock option which either adds prexisting stocks or creates a new instance of it
- Sell stock option, sells existing at a price and calculates gains/losses
- Update feature to update prices and calculate gains/losses
-Search Feature, to find a specific stock through, symbol, keyword(s), price ranges
- getGain feature to return the total gains 
- And lastly to simply exit the program  

************
Compilation
************
Open package on net beans and hit builder


***********************
Running the program(s)
***********************
Open package on net beans and hit debugger
Requires arguments for filename, else program will exit!

*****************
Known Limitations
*****************
No known limitations thus far.

All inputs will not accept invalid values, depending on the function some will not accept empty values for example symbol will not accept empty values when intializing a list as per the assignment rules, 
Everything is programmed with try method.

*Must contain a fileName in comand line 

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

update :
1. user enters something that isn't an integer
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

Test Cases : 

1. Entering invalid menu inputs (Example : 7, $, bye, goodbye, exit)
	Solution : Will only exit if user types 6. or "q", "quit" 	
	case does not matter.
	If user picks the the right number corresponding to the 	
	menu, it will bring up that function.
	Otherwise will show the user the menu once again and ask for 	
	a valid input!

2. Entering nothing in menu, 
	Solution : should take Anything other then 1-6, q and quit 	
	as an invalid input.

3. Entering negative values where integers are required, 
	Solution : negative quantity/price is invalid and will not 	
	be accepted as an input from the user, this can easily be 	
	implemented by using a while loop which only takes positive 	
	integer and or "0"
4. Entering Nothing in options that require a user input
	Solution : keep prompting user to enter something and while 	
	loop till String is no longer empty, if String is empty
	return invalid input!
5. Trying to search,sell,update,etc in an empty list
	Solution : If no list exists just exit the function and say 	
	list doesn't eixst
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

1. Better clear function, The clear function i use, spams \n's which makes the output look somewhat unattractive, the reason i had implemented it is so that the user does not get confused when iterating through my program.

2. Format some outputs in a better fashion, a lot of my outputs look very rushed and plain, if i had time i would make sure i could format it better for better user understability.

3. Due to the lack of time and  mid term season, I ended up repeating quite a bit of code, I did go back and clean up a over 300-400 lines of repeated codes by creating function for them but 
I still believe there is atleast another 50-100 lines of code i can reduce from my program.

4. Although the assignment states to use sequential search, it does bother me that it is a very inefficient  method of searching, if I had better knowledge and understanding and more time 
I would definitely use a better search option, and also would use functions like insertSorted to make iterating through the list a lot easier and quicker!

5. Continously remakes the HashMap functions over and over again, if i had more time, i would definitely create much more efficient functions which remove/add iterations to the
HashMap when needed, instead of creating it every time the menu loops!

6. The symbol search method still implements sequential search, which from according to the assignment description is what was expected, but if i had time i would have
used the HashMap in the search method with symbol aswell!



