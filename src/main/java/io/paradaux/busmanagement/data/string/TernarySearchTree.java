package io.paradaux.busmanagement.data.string;

import org.checkerframework.checker.nullness.qual.Nullable;

public class TernarySearchTree<T> {

    private TSTNode root;

    public TernarySearchTree() {
        root = null;
    }

//    @Nullable
//    public String search(String str) {
//        return root == null || root.search(str) == null ? null : str;
//    }


//    public boolean add(String element, T value) {
//        if (root == null) {
//            root = new TSTNode(element, value);
//            return true;
//        } else {
//            return root.add
//        }
//    }
    class TSTNode {
        private Character character;
        private T value;

        private boolean isKey;

        private TSTNode left;
        private TSTNode middle;
        private TSTNode right;

        public TSTNode(String key, T value) {
            if (key.isEmpty() || value == null) {
                throw new IllegalArgumentException();
            }

            character = key.charAt(0);

            if (key.length() > 1) {
                // Stores the rest of the key in the middle-link chain
                middle = new TSTNode(key.substring(1), value);
            } else {
                this.value = value;
            }
        }
    }
}
