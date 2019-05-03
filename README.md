# Procedural Generation
A WIP project to have a go at procedural generation terrain.

User inputs:
 - To generate initial map
   - Width and height in grid units
   - Percentage water
   - Minumum terrain height
   - Maximum terrain height
 - To smooth the map
   - Max adjacent height difference
     - Max height difference bettween two grid points next to each other (kind of like mini cliffs)
   - Average adjacent height difference
     - Cannot be more than max diff
     - Height difference will be closer to this and no more than max


Output a top down image of a generated map (minimap style)
Plans to output to more formats
