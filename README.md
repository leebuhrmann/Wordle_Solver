# GENERAL DESCRIPTION

A program that solves Wordle.

# TERMINOLOGY

Position is referring to a specific location in the word, counting left to right. In the word “ C R O S S ” the letter ‘C’ is in the first position, the letter ‘O’ is in the third position, and the second ’S’ is in the fifth position.

# WHAT IS WORDLE

Once a day, Wordle has a secret five-letter word (the solution) that must be guessed to win the game, and a player only gets six guesses for that day's solution. When a player makes a guess, Wordle will inform them if the letter does exist in the solution, and if it does exist it will inform them if it was in the correct position. A correct letter is a letter that exists and is in the correct position; it is highlighted green. A missed letter is a letter that exists but is in the incorrect position; it is highlighted yellow. An incorrect letter is a letter that was guessed, but does not exist in the solution at any position.

S T O N E  >> first guess

C R O S S  >> second guess

WEIGHTED LETTER PROBABILITY

Wordle has a list of every word that could be the winning word and a list where the word could be accepted, but will never be the winning word. Using the list of winning words, we will create a weighted list of letters in each position. The weights will be very simple, a list that contains the total number of times each letter was found in each of the five positions within the list of winning words.

With this dictionary, these letters would have these positional weights:

	position | 1 | 2 | 3 | 4 | 5 |
	-      A | 1 | 1 | 2 | 0 | 0 | 



ALGORITHM

But how does the program make guesses on its own? Below will describe how Wordle_Solver will make its guess with correct letters and missed letters. It will also go over how it determines what it will guess for the blank positions that remain. It does this with a recursive and layered algorithm as described below.

Correct Letters Layer

The first stop when creating a guess. Simply place the correct letters in the correct positions for its current guess then pass the guess to the Missed Letters Layer.

Missed Letters Layer

Takes in the guess passed by the Correct Letter Layer and picks a single letter from its known missed letters list. Find the highest weighted position for that picked letter and place it there if that letter has not yet been attempted in that position. Once a position has been found for the letter recurs into itself until all letters in the missed letters list have been placed. Once all letters from the missed letters list have been placed, pass to Blanks and Guessing Layer.

Blanks and Guessing Layer

Any position left at this point is known as a blank.

The steps for filling blanks are as follows:
Pick the position farthest to the left
Choose the highest weighted letter in that position that is an incorrect letter
If the position is not the last blank position on the right, move to the next blank position to the right and repeat steps 1-3

Once all blanks are filled guessing goes as follows:
Check to see if guess is a word
If it is a word, make submit guess and start from the top
If guess was not a word choose the farthest right blank.
If the letter is not the least weighted letter, change the letter to the next heaviest letter.
If the letter is the least weighted letter in that position, make it the heaviest weighted letter, move the next blank to the left, and drop it to the next heaviest letter.





