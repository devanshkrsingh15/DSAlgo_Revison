package Tree;

import java.util.*;

public class GenericTreeNode {
    int val;
    List<GenericTreeNode> child;

    GenericTreeNode() {
    }

    GenericTreeNode(int val) {
        this.val = val;
        this.child = new ArrayList<>();
    }

}
