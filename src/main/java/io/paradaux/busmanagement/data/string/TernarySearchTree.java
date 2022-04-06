package io.paradaux.busmanagement.data.string;

import org.checkerframework.checker.nullness.qual.Nullable;

public class TernarySearchTree {

    private TSTNode root;

    public TernarySearchTree() {
        root = null;
    }


    public TSTNode insert(String str) {
        root = insert(this.root, str.toCharArray(), 0);
        return root;
    }


    private TSTNode insert(TSTNode node, char[] letters, int i) {
        char letter = letters[i];

        if (node == null) {
            node = new TSTNode(letter);
        }

        if (letter < node.getCharacter()) {
            node.setLeft(insert(node.getLeft(), letters, i));
        } else if (letter > node.getCharacter()) {
            node.setRight(insert(node.getRight(), letters, i));
        } else {
            if (i + 1 < letters.length) {
                node.setCenter(insert(node.getCenter(), letters, i+1));
            }
        }

        return node;
    }


    public boolean search(String target) {
        if (target == null) {
            return false;
        }

        return search(root, target.toCharArray(), 0);
    }


    private boolean search(TSTNode node, char[] letters, int i) {
        if (node == null) {
            return false;
        }

        char letter = letters[i];

        if (letter < node.getCharacter()) {
            return search(node.getLeft(), letters, i);
        } else if (letter > node.getCharacter()) {
            return search(node.getRight(), letters, i);
        } else {
            if (i == letters.length - 1) {
                return node.isEnd();
            }

            return search(node.getCenter(), letters, i);
        }
    }

    private static class TSTNode {
        private final Character character;
        private boolean isEnd;

        private TSTNode left, center, right;

        public TSTNode(Character character) {
            this(character, false);
        }

        public TSTNode(Character character, boolean isEnd) {
            this.character = character;
            this.isEnd = isEnd;
        }

        boolean isLeaf() {
            return left == null && right == null && center == null;
        }

        public Character getCharacter() {
            return character;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public TSTNode getLeft() {
            return left;
        }

        public TSTNode getCenter() {
            return center;
        }

        public TSTNode getRight() {
            return right;
        }

        public TSTNode setEnd(boolean end) {
            isEnd = end;
            return this;
        }

        public TSTNode setLeft(TSTNode left) {
            this.left = left;
            return this;
        }

        public TSTNode setCenter(TSTNode center) {
            this.center = center;
            return this;
        }

        public TSTNode setRight(TSTNode right) {
            this.right = right;
            return this;
        }
    }


}
