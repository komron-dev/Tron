# Tron-Inspired Java Game

## Overview

This project is a Tron-inspired light cycle game implemented in Java using Java Swing for the graphical user interface (GUI). The game features a two-player mode where each player controls a bike that leaves a trail, and the objective is to avoid crashing into trails or the game boundaries. The game also includes a database persistence system for storing high scores and game statistics.

## Features

- **Two-Player Mode**: Compete against another player in real-time.
- **Grid-Based Movement**: Players move in four directions (up, down, left, right) without stopping.
- **Collision Detection**: The game ends when a player collides with a trail or the boundary.
- **Score Tracking**: High scores are stored in a database.
- **User-Friendly GUI**: Implemented using Java Swing for smooth gameplay experience.

## Technologies Used

- **Java** (Core game logic and GUI)
- **Java Swing** (User interface)
- **JDBC & PostgreSQL** (Data persistence for scores)
- **Object-Oriented Design Principles**
- **Multithreading** (For handling real-time movement and updates)

## How to Play

- Player 1 controls their bike using `W, A, S, D` keys.
- Player 2 controls their bike using arrow keys (`↑, ↓, ←, →`).
- The game continues until one player crashes into a trail or the boundary.
- The winner is the last player remaining.
- Scores are automatically saved to the database.

## Database Usage

The game uses an PostgreSQL database to store scores. The schema consists of a `scores` table with the following fields:

- `id` (INTEGER, PRIMARY KEY, AUTOINCREMENT)
- `player_name` (TEXT)
- `score` (INTEGER)

