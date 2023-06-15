# Paper Bag Princess
Lancaster University Computer Science (SCC-210) Second year group project
## The Game
Task was to make a 2D arcade style game, written in Java with no external libraries allowed, apart from LibGDX. It was made over October through to March with five members on the team.

Paper Bag Princess is a game where you play as a princess, who's King needs saving. You start in a room, and you navigate through the levels to reach another house to beat the final zombie boss, a zombie princess. You have two attacks, left click for melee and right click for a fireball, which uses up your mana.

## The level creator
All the levels in the game are stored as text files, this makes it so we don't need a class for each level, eliminating lots of repeated code. 

Making a level creator for these text files was the best way forward, as it would allow us to make lots of levels in a smaller amount of time.

### Video for the lazy:
[![Video of game, linking to youtube](https://img.youtube.com/vi/y95FuIaNx5c/0.jpg)](https://youtu.be/y95FuIaNx5c)



## Running the game
1. Clone the repo
2. CD into the repo `cd GROUP-25`
3. Run with `./gradlew desktop:run`
