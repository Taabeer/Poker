# Poker game 

## Overview
The game allows players to bet between 1-5 tokens, with winnings scaled accordingly.

## File Structure
The program is organized into several classes: `Card`, `Deck`, `Game`, `Player`, and a test class `PokerTest`. 

## Implementation

### Card Class
The `Card` class represents a standard playing card with a suit and rank. It implements the `Comparable<Card>` interface and provides methods for comparing ranks, suits, and an overall comparison. The `toString` method generates a user-friendly representation of the card.

### Deck Class
The `Deck` class represents a standard deck of 52 cards. It includes methods for shuffling the deck and dealing cards to players. The `shuffle` method employs a randomization algorithm to ensure a fair distribution of cards.

### Game Class
The `Game` class orchestrates the gameplay. It handles player bets, card dealing, replacing cards, and determining the hand's rank. The game supports two versions with different constructors, allowing for explicit parameter input for testing purposes. The `play` method guides the player through the game, displaying current cards, allowing card replacement, and calculating winnings based on hand rankings.

### Player Class
The `Player` class represents the player in the game. It includes methods for managing the player's hand, making bets, receiving winnings, and displaying the current hand. The `bets` method ensures valid bet amounts between 1-5 tokens, prompting the user for a different amount if needed. The `winnings` method adjusts the player's bankroll based on the calculated odds.

## Usage
To play the Poker game, follow these steps:

1. Run the `PokerTest` class.
2. The game will prompt you to enter the number of tokens you want to bet (between 1-5).
3. Five cards will be dealt, and the current hand will be displayed.
4. Choose whether to replace any cards by entering 1 for yes or 0 for no.
   - If yes, specify the number of cards to replace and their corresponding indices.
5. The final hand and its rank will be displayed, along with any winnings added to your bankroll.
