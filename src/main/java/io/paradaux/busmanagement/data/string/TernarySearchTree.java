package io.paradaux.busmanagement.data.string;

/**
 * A {@link TernarySearchTree} is a type of prefix tree in which nodes are arranged in a structure similar to a binary
 * search tree with the addition of an additional child node. It's often used as an associative map structure for
 * incremental string search, which is the case for this implementation. It is intended for use in incremental string
 * search.
 * @implNote This tree structure is not generic, and expects strings to be stored.
 * */
public class TernarySearchTree {

    private TSTNode root;

    public TernarySearchTree() {
        root = null;
    }


    /**
     * Insert as many words as desired into the TST, as varargs or a String[].
     * @param words The words to be added to the tree.
     * */
    public void insert(String... words) {
        for(String word : words) {
            root = insert(root, word.toCharArray(), 0);
        }
    }


    /**
     * Insert a node down into the tree, creating a new child node if it doesn't exist.
     * @param node The node from which we're starting our traversal / adding on to.
     * @param letters The sequence of letters from a String we wish to add to the tree.
     * @param i The index of the letter we are attempting to add.
     * @return The added node.
     * @implNote This is a recursive method.
     * */
    public TSTNode insert(TSTNode node, char[] letters, int i) {
        if (node == null) {
            node = new TSTNode(letters[i]);
        }

        if (letters[i] < node.character) {
            node.left = insert(node.left, letters, i);
        } else if (letters[i] > node.character) {
            node.right = insert(node.right, letters, i);
        } else {
            if (i + 1 < letters.length) {
                node.centre = insert(node.centre, letters, i + 1);
            } else {
                node.isEnd = true;
            }
        }
        return node;
    }


    public String[] search(String word) {
        if (word == null || word.charAt(0) == ' ') {
            return new String[]{};
        }

        StringBuilder builder = new StringBuilder();

        TSTNode lastNode = search(root, word.toCharArray(), 0);
        makeSuggestions(lastNode, "", builder, word);

        if (builder.length() < 1) {
            return new String[]{};
        }

        return builder.
                toString()
                .split("\n");
    }


    /**
     * Searches recursively for a sequence of characters {@param letters} and follows the character sequence down the
     * tree, returning the node which contains the next letter in the sequence.
     * @param node The node from which we're starting our traversal / adding on to.
     * @param letters The sequence of letters from a String we wish to add to the tree.
     * @param i The index of the letter we are attempting to add.
     * @return The next node to be searched or the node containing the final letter.
     * */
    private TSTNode search(TSTNode node, char[] letters, int i) {
        if (node == null) {
            return null;
        }

        if (letters[i] < node.character) {
            return search(node.left, letters, i);
        } else if (letters[i] > node.character) {
            return search(node.right, letters, i);
        } else {
            if (i == letters.length - 1) {
                return node;
            } else {
                return search(node.centre, letters, i + 1);
            }
        }
    }


    /**
     * Generates suggestions based on the remaining possible nodes which could be traversed.
     * @param node The next node to traverse
     * @param str The string which we're forming to build a suggestion
     * @param builder A {@link StringBuilder} which will pass the generated strings between executions
     * @param pattern The pattern to search for.
     * */
    private void makeSuggestions(TSTNode node, String str, StringBuilder builder, String pattern) {
        if (node != null) {
            makeSuggestions(node.left, str, builder, pattern);
            str = str + node.character;
            if (node.isEnd) {
                if (pattern.length() == 1) {
                    if (pattern.equals(str.substring(0, 1))) {
                        builder.append(pattern)
                                .append(str.substring(1))
                                .append('\n');
                    }
                } else {
                    builder.append(pattern)
                            .append(str.substring(1))
                            .append("\n");
                }
            }

            makeSuggestions(node.centre, str, builder, pattern);
            str = str.substring(0, str.length() - 1);
            makeSuggestions(node.right, str, builder, pattern);
        }
    }


    /**
     * Represents a node in our {@link TernarySearchTree} containing the character stored at this node, whether
     * this node is at the end of the chain as well as its three child nodes, represented as being on the left, right or
     * centre.
     * */
    private static class TSTNode {

        private final Character character;
        private boolean isEnd;
        private TSTNode left, centre, right;

        public TSTNode(char data) {
            this.character = data;
            this.isEnd = false;
        }
    }
}