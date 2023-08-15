# GENERAL DESCRIPTION

A program that solves Wordle. It uses data from available dictionaries of five letter words and a recursive, layered algorithm to create guesses. Almost always wins within six guesses.

# THE POINT OF THIS PROJECT

I wanted to create a project that not only exercised my foundational Java knowledge, but also challenged my problem solving skills. While there are definitely more innovative ways to do this project (AI comes to mind), I chose to construct an algorithm that pushed my understanding of data collections, programming structures, and logic.

# TERMINOLOGY

**Position** is referring to a specific location in the word, counting left to right. In the word “ C R O S S ” the letter ‘C’ is in the first position, the letter ‘O’ is in the third position, and the second ’S’ is in the fifth position.

If a letter is said to **exist**, this is shorthand for saying that this letter has been guessed and has been found to exist in the current solution.

# WHAT IS WORDLE

Once a day, Wordle has a secret five-letter word (the **solution**) that must be guessed to win the game, and a player only gets six guesses for that day's solution. When a player makes a guess, Wordle will inform them if the letter does *exist in the solution*, and if it does exist it will inform them if it was *in the correct position*. A **correct** letter is a letter that exists and is in the correct position; it is highlighted green. A **missed** letter is a letter that exists but is in the incorrect position; it is highlighted yellow. An **incorrect** letter is a letter that was guessed, but does not exist in the solution at any position; it is highlighted grey.

![image](https://github.com/leebuhrmann/Wordle_Solver/assets/61166759/9f1f7eec-eb99-47a0-9d0d-3591e93c0f8c)

*An example of a game of Wordle; solved in five guesses.*
# WEIGHTED LETTER PROBABILITY

Wordle has a list of every word that could be the winning word and a list where the word could be accepted, but will never be the winning word. Using the list of winning words, we will create a weighted list of letters in each position. The weights will be very simple, a list that contains the total number of times each letter was found in each of the five positions within the list of winning words.

Here is an example:

Imagine we had a dictionary of acceptable words and there were only five words in that dictionary.

1. Feast
2. Crash
3. Arbor
4. Tests
5. Sales

With this dictionary, these letters would have these **positional weights**:

	position |-1-|-2-|-3-|-4-|-5-|
	letter A | 1 | 1 | 2 | 0 | 0 |
	letter B | 0 | 0 | 1 | 0 | 0 |
	letter C | 1 | 0 | 0 | 0 | 0 |
	letter E | 0 | 2 | 0 | 1 | 0 |
	letter F | 1 | 0 | 0 | 0 | 0 |
	letter H | 0 | 0 | 0 | 0 | 1 |
	letter L | 0 | 0 | 1 | 0 | 0 |
	letter O | 0 | 0 | 0 | 1 | 0 |
	letter R | 0 | 2 | 0 | 0 | 1 |
	letter S | 1 | 0 | 1 | 2 | 2 |
	letter T | 1 | 0 | 0 | 1 | 1 |

# ALGORITHM

But how does the program make guesses on its own? Below will describe how Wordle_Solver will make its guess with correct letters and missed letters. It will also go over how it determines what it will guess for the blank positions that remain. It does this with a recursive and layered algorithm as described below.

### Entering the Algorithm

Before constructing a guess the program first gathers what is knows in two lists of data, the *correct letters list* and the *missed letters list*. The program also knows in what position each letter has been guessed,  if those positions were correct or incorrect, and the positional weights of every letter.
### Correct Letters Layer

This is the first stop when creating a guess. Simply place all the letters from the correct letters list in their correct positions for the current guess then pass that guess to the Missed Letters Layer.

### Missed Letters Layer

Takes in the guess passed by the Correct Letter Layer and picks a single letter from the missed letters list. Find the highest weighted position for that picked letter and place it there if that letter has not yet been attempted in that position. Once an allowable position has been found for the letter, recurs into Missed Letters Layer until all letters in the missed letters list have been placed. Once all letters from the missed letters list have been placed, pass the currently constructed guess to Blanks and Guessing Layer.

### Blanks and Guessing Layer

Takes in the guess passed by Missed Letters Layer and creates a list of positions that are still blank called *blanks list*. From left to right, fills in the blanks positions with the letter that is most likely to occur in that position and then checks to see if it constructed a real word. If the word was real it submits it as an acceptable guess to the game.

If the word was not real it changes the right most blank to the next most likely letter in that position and tries again. If none of the letters in that position create a word, it changes the next most left blank and tried all the letters in the right most blank again.

If no combination of letters in the blank positions create a word, the Blanks and Guessing Layer return to the Missed Letters Layer and tell it to attempt a new combination. The Missed Letters Layer then creates a new guess in a method similar to Blanks and Guessing Layer, changing one missed letter to the next most likely position.
