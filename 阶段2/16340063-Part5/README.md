# READ ME

---

Each class has a runner.

Run the runner will show the GUI and can select different grid by set button.

---

## Part 5 复杂度分析

---

|Methods|`SparseGridNode`<br>Version|`LinkedList<OccupantInCol>`<br>Version|`HashMap`<br>Version|`TreeMap`<br>Version|
|--|:--:|:--:|:--:|:--:|
|`getNeighbors`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|
|`getEmptyAdjacentLocations`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|
|`getOccupiedAdjacentLocations`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|
|`getOccupiedLocations`|O(r+n)|O(r+n)|O(n)|O(n)|
|`get`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|
|`put`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|
|`remove`|O(c)|O(c)|O(1)|O(log<sub>n</sub>)|