import java.util.Scanner;

class Node {

  /*
  red color ==> true, 
  black color ==> false
  New Node is always red.
  */

  Node leftChild; 
  Node rightChild;
  int val;
  boolean color;

  Node(int val) {
    this.val = val;
    leftChild = null;
    rightChild = null;
    color = true;
  }
}

public class LeftSideBlackRedTree {

  static Node root = null;

  public static void main(String[] args) {

    char input;
    Scanner inputScanner = new Scanner(System.in);
    LeftSideBlackRedTree node = new LeftSideBlackRedTree();

    System.out.println("The program is running.");

    do {
      System.out.println("Insert integer number: ");
      root = node.insert(root, inputScanner.nextInt());
      node.printTree(root);
      System.out.println("\nDo you want to continue? (insert y or n)");
      input = inputScanner.next().charAt(0);
    } while (input == 'Y' || input == 'y');

    inputScanner.close();

  }

  void printTree(Node node) {
    if (node != null) {
      printTree(node.leftChild);
      String color = "red";
      if (node.color == false) color = "black";
      System.out.print(node.val + "-" + color + " ");
      printTree(node.rightChild);
    }
  }

  boolean checkIsRed(Node node) {
    if (node == null) return false;
    return (node.color == true);
  }

  void changeColor(Node nodeFirst, Node nodeSecond) {
    boolean temp = nodeFirst.color;
    nodeFirst.color = nodeSecond.color;
    nodeSecond.color = temp;
  }

  Node leftTurn(Node node) {

    System.out.printf("The tree has been turned left:\n");

    Node child = node.rightChild;
    Node childLeft = child.leftChild;

    child.leftChild = node;
    node.rightChild = childLeft;

    return child;
  }

  Node rightTurn(Node myNode) {

    System.out.printf("The tree has been turned right:\n");
    Node child = myNode.leftChild;
    Node childRight = child.rightChild;

    child.rightChild = myNode;
    myNode.leftChild = childRight;

    return child;
  }

  Node insert(Node node, int value) {

    if (node == null) return new Node(value);

    if (value < node.val) {
      node.leftChild = insert(node.leftChild, value);
    } else if (value > node.val) {
      node.rightChild = insert(node.rightChild, value);
    } else {
      return node;
    }

    if (checkIsRed(node.rightChild) && !checkIsRed(node.leftChild)) {
      node = leftTurn(node);
      changeColor(node, node.leftChild);
    }

    if (checkIsRed(node.leftChild) && checkIsRed(node.leftChild.leftChild)) {
      node = rightTurn(node);
      changeColor(node, node.rightChild);
    }

    if (checkIsRed(node.leftChild) && checkIsRed(node.rightChild)) {
      node.color = !node.color;
      node.leftChild.color = false;
      node.rightChild.color = false;
    }

    return node;
  }
}