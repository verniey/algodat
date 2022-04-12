package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Timer;
import main.java.framework.Verifier;

public class VerifierImplementation extends Verifier<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation> {

    @Override
    public CommonResultImplementation solveProblemUsingStudentSolution(CommonInstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        InstanceType instanceType = instance.getInstanceType();
        Timer timer = new Timer();
        switch (instanceType) {
            case CHAR_AT_TEST:
                CharAtTestInstanceImplementation charAtTestInstanceImplementation = (CharAtTestInstanceImplementation) instance;
                timer.start();
                char result = studentSolution.getCharAt(
                        charAtTestInstanceImplementation.getWord(),
                        charAtTestInstanceImplementation.getIndex()
                );
                timer.stop();
                return new CharAtTestResultImplementation(timer.getDuration(), result);
            case LOOKUP_KEY_TEST:
                InnerNode root = new InnerNode();
                root.attachInnerNode('t');
                InnerNode tChildNode = (InnerNode) root.getChild('t');
                LeafNode testLeafNode = new LeafNode("test", studentSolution);
                LeafNode tLeafNode = new LeafNode("t", studentSolution);
                tChildNode.attachLeafNode('e', testLeafNode);
                tChildNode.attachLeafNode('{', tLeafNode);
                LookupKeyTestInstanceImplementation lookupKeyTestInstanceImplementation = (LookupKeyTestInstanceImplementation) instance;
                timer.start();
                boolean isContained = studentSolution.lookupKey(root, new Key(lookupKeyTestInstanceImplementation.getKey(), studentSolution));
                timer.stop();
                return new LookupKeyTestResultImplementation(timer.getDuration(), isContained);
            case INSERT_KEY_TEST:
                InsertKeyTestInstanceImplementation insertKeyTestInstanceImplementation = (InsertKeyTestInstanceImplementation) instance;
                InnerNode emptyTestRoot = new InnerNode();
                String[] testKeys = insertKeyTestInstanceImplementation.getKeys();
                timer.start();
                for (int i = 0; i < testKeys.length && i < testKeys.length; i++) {
                    studentSolution.insertKey(emptyTestRoot, new LeafNode(testKeys[i], studentSolution));
                }
                timer.stop();
                return new InsertKeyTestResultImplementation(timer.getDuration(), emptyTestRoot, studentSolution);
            case INSERT_AND_LOOKUP:
                InsertAndLookupInstanceImplementation insertAndLookupInstanceImplementation = (InsertAndLookupInstanceImplementation) instance;
                InnerNode emptyRoot = new InnerNode();
                String[] keys = insertAndLookupInstanceImplementation.getKeys();
                BinaryTreeNode binaryTreeRoot = null;
                timer.start();
                for (int i = 0; i < keys.length && i < insertAndLookupInstanceImplementation.getNumberOfKeys(); i++) {
                    studentSolution.insertKey(emptyRoot, new LeafNode(keys[i], studentSolution));
                    if (binaryTreeRoot == null) {
                        binaryTreeRoot = new BinaryTreeNode(keys[i]);
                    } else {
                        binaryTreeRoot.insertKey(keys[i]);
                    }
                }
                long averageTrieLookupTime = 0;
                long averageTreeLookupTime = 0;
                long averageListLookupTime = 0;
                for (int i = 0; i < keys.length && i < insertAndLookupInstanceImplementation.getNumberOfKeys(); i++) {
                    Timer timerLookupTrie = new Timer();
                    timerLookupTrie.start();
                    boolean keyFoundInTrie = studentSolution.lookupKey(emptyRoot, new Key(keys[i], studentSolution));
                    timerLookupTrie.stop();

                    Timer timerLookupTree = new Timer();
                    timerLookupTree.start();
                    binaryTreeRoot.lookupKey(keys[i]);
                    timerLookupTree.stop();

                    Timer timerLookupList = new Timer();
                    timerLookupList.start();
                    lookupKeyInList(keys, insertAndLookupInstanceImplementation.getNumberOfKeys(), keys[i]);
                    timerLookupList.stop();

                    long timeTrieSum = averageTrieLookupTime * i;
                    timeTrieSum += timerLookupTrie.getDuration();
                    averageTrieLookupTime = timeTrieSum / (i + 1);

                    long timeTreeSum = averageTreeLookupTime * i;
                    timeTreeSum += timerLookupTree.getDuration();
                    averageTreeLookupTime = timeTreeSum / (i + 1);

                    long timeListSum = averageListLookupTime * i;
                    timeListSum += timerLookupList.getDuration();
                    averageListLookupTime = timeListSum / (i + 1);

                    if (!keyFoundInTrie) {
                        timer.stop();
                        return new InsertAndLookupResultImplementation(timer.getDuration(), insertAndLookupInstanceImplementation.getNumberOfKeys(), insertAndLookupInstanceImplementation.getKeyGroup(), averageTrieLookupTime, averageTreeLookupTime, averageListLookupTime, keys[i]);
                    }
                }
                timer.stop();
                return new InsertAndLookupResultImplementation(timer.getDuration(), insertAndLookupInstanceImplementation.getNumberOfKeys(), insertAndLookupInstanceImplementation.getKeyGroup(), averageTrieLookupTime, averageTreeLookupTime, averageListLookupTime,  null);
        }
        return null;
    }

    private boolean lookupKeyInList(String[] keys, int numberOfKeys, String key) {
        for (int i = 0; i < keys.length && i < numberOfKeys; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    private class BinaryTreeNode {

        private String key;
        private BinaryTreeNode leftChild;
        private BinaryTreeNode rightChild;

        public BinaryTreeNode(String key) {
            this.key = key;
        }

        public void insertKey(String key) {
            int comparisonResult = key.compareTo(this.key);
            if (comparisonResult < 0) {
                if (this.leftChild != null) {
                    this.leftChild.insertKey(key);
                } else {
                    this.leftChild = new BinaryTreeNode(key);
                }
            } else if (comparisonResult > 0) {
                if (this.rightChild != null) {
                    this.rightChild.insertKey(key);
                } else {
                    this.rightChild = new BinaryTreeNode(key);
                }
            }
        }

        public boolean lookupKey(String key) {
            int comparisonResult = key.compareTo(this.key);
            if (comparisonResult == 0) {
                return true;
            } else if (comparisonResult < 0) {
                if (this.leftChild != null) {
                    return this.leftChild.lookupKey(key);
                } else {
                    return false;
                }
            } else {
                if (this.rightChild != null) {
                    return this.rightChild.lookupKey(key);
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public Report verifyResult(CommonInstanceImplementation instance, CommonResultImplementation result) {
        InstanceType instanceType = instance.getInstanceType();
        switch (instanceType) {
            case CHAR_AT_TEST:
                CharAtTestInstanceImplementation charAtTestInstanceImplementation = (CharAtTestInstanceImplementation) instance;
                CharAtTestResultImplementation charAtTestResultImplementation = (CharAtTestResultImplementation) result;
                if (charAtTestInstanceImplementation.getExpectedChar() == charAtTestResultImplementation.getResult()) {
                    return new Report(true, "");
                } else {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Char expected to be " + charAtTestInstanceImplementation.getExpectedChar() + " but was " + charAtTestResultImplementation.getResult() + ".");
                }
            case LOOKUP_KEY_TEST:
                LookupKeyTestInstanceImplementation lookupKeyTestInstanceImplementation = (LookupKeyTestInstanceImplementation) instance;
                LookupKeyTestResultImplementation lookupKeyTestResultImplementation = (LookupKeyTestResultImplementation) result;
                if (lookupKeyTestInstanceImplementation.isExpectedToBeContained() == lookupKeyTestResultImplementation.isContained()) {
                    return new Report(true, "");
                } else {
                    if (lookupKeyTestInstanceImplementation.isExpectedToBeContained()) {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": Key '" + lookupKeyTestInstanceImplementation.getKey() + "' expected to be found but was not.");
                    } else {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": Key '" + lookupKeyTestInstanceImplementation.getKey() + "' not expected to be found but was.");
                    }
                }
            case INSERT_KEY_TEST:
                InsertKeyTestInstanceImplementation insertKeyTestInstanceImplementation = (InsertKeyTestInstanceImplementation) instance;
                InsertKeyTestResultImplementation insertKeyTestResultImplementation = (InsertKeyTestResultImplementation) result;
                String[] testKeys = insertKeyTestInstanceImplementation.getKeys();
                InnerNode root = insertKeyTestResultImplementation.getRoot();
                if (testKeys.length == 1) {
                    String key = testKeys[0];
                    LeafNode leafNode = null;
                    for (char c = 'a'; c < 'a' + 27; c++) {
                        Node childNode = root.getChild(c);
                        if (c == key.charAt(0)) {
                            if (childNode == null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node is expected to be attached directly to the root at '" + key.charAt(0) + "', since only the key '" + key + "' has been inserted. No node was found.");
                            } else if (!childNode.isLeaf()) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node is expected to be attached directly to the root at '" + key.charAt(0) + "', since only the key '" + key + "' has been inserted. An inner node was found instead.");
                            } else {
                                leafNode = (LeafNode) childNode;
                            }
                        } else {
                            if (childNode != null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": No node is expected to be attached directly to the root at '" + c + "', since only the key '" + key + "' has been inserted. However, a node was found.");
                            }
                        }
                    }
                    if (leafNode != null) {
                        if (leafNode.containsKey(new Key(key, insertKeyTestResultImplementation.getStudentSolutionImplementation()))) {
                            return new Report(true, "");
                        } else {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node with the key '" + key + "' was expected but another key was found.");
                        }
                    }
                } else {
                    // Specifically for instances 3, 4 and 5.
                    boolean sameKeyTwice = testKeys.length == 3;
                    InnerNode innerNode = null;
                    for (char c = 'a'; c < 'a' + 27; c++) {
                        Node childNode = root.getChild(c);
                        if (c == 't') {
                            if (childNode == null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": An inner node is expected to be attached directly to the root at 't', since only the keys 'test' and 't' have been inserted. No node was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            } else if (childNode.isLeaf()) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": An inner node is expected to be attached directly to the root at 't', since only the keys 'test' and 't' have been inserted. A leaf node was found instead." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            } else {
                                innerNode = (InnerNode) childNode;
                            }
                        } else {
                            if (childNode != null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": No node is expected to be attached directly to the root at '" + c + "', since only the keys 'test' and 't' have been inserted. However, a node was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            }
                        }
                    }
                    LeafNode leafNodeE = null;
                    LeafNode leafNode_ = null;
                    for (char c = 'a'; c < 'a' + 27; c++) {
                        Node childNode = innerNode.getChild(c);
                        if (c == 'e' || c == '{') {
                            if (childNode == null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node is expected to be attached at '" + c + "' to the child node that is attached to the root at 't', since only the keys 'test' and 't' have been inserted. No node was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            } else if (!childNode.isLeaf()) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node is expected to be attached at '" + c + "' to the child node that is attached to the root at 't', since only the keys 'test' and 't' have been inserted. An inner node was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            } else {
                                if (c == 'e') {
                                    leafNodeE = (LeafNode) childNode;
                                } else {
                                    leafNode_ = (LeafNode) childNode;
                                }
                            }
                        } else {
                            if (childNode != null) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": No node is expected to be attached at '" + c + "' to the child node that is attached to the root at 't', since only the keys 'test' and 't' have been inserted. However, a node was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                            }
                        }
                    }
                    if (leafNodeE != null && leafNode_ != null) {
                        if (!leafNodeE.containsKey(new Key("test", insertKeyTestResultImplementation.getStudentSolutionImplementation()))) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node with the key 'test' was expected to be attached at 'e' but another key was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                        }
                        if (!leafNode_.containsKey(new Key("t", insertKeyTestResultImplementation.getStudentSolutionImplementation()))) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": A leaf node with the key 't' was expected to be attached at '{' but another key was found." + (sameKeyTwice ? " Keep in mind that 'test' was tried to be inserted twice and that insertion should be idempotent." : ""));
                        }
                        return new Report(true, "");
                    }
                }
                return new Report(false, "Error");
            case INSERT_AND_LOOKUP:
                InsertAndLookupResultImplementation insertAndLookupResultImplementation = (InsertAndLookupResultImplementation) result;
                if (insertAndLookupResultImplementation.getLastKeyNotFound() != null) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": The key '" + insertAndLookupResultImplementation.getLastKeyNotFound() + "' was inserted but not found afterwards. Keep in mind that the error can either be in your insert or your lookup implementation.");
                }
                return new Report(true, "");
        }
        return null;
    }
}
