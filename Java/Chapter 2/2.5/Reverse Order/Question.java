/*Question 2.5 You have two numbers represented by a linked list, where each node contains a single digit.
The digits are stored in reverse order, such that the Ts digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input:(7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
Output: 2 -> 1 -> 9.That is, 912.
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem.
EXAMPLE
Input:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
Output: 9 -> 1 -> 2.That is, 912.*/

import MyLibrary.LinkedListNode;

public class Question {
    //the digits are sorted in reverse order
    public static LinkedListNode addLists(LinkedListNode a, LinkedListNode b)
    {
        if(a == null | b == null)
            return null;

        LinkedListNode result = null;
        LinkedListNode resultEnd = null;
        boolean isOverflow = false;
        int sum = 0;
        while(a != null)
        {
            sum = a.data + b.data;

            if(isOverflow)
            {
                sum++;
                isOverflow = false;
            }

            if(sum >= 10)
            {
                isOverflow = true;
                sum = sum - 10;
            }

            if(result == null)
            {
                result = new LinkedListNode(sum,null,null);
                resultEnd = result;
            }
            else
            {
                LinkedListNode node = new LinkedListNode(sum,null,null);
                resultEnd.next = node;
                resultEnd = node;
            }
            a = a.next;
            b = b.next;
        }
        if(isOverflow)
        {
            LinkedListNode node = new LinkedListNode(1,null,null);
            resultEnd.next = node;
        }
        return result;
    }

    public static int ListToInt(LinkedListNode s,boolean isReverse)
    {
        if(s ==null)
            return 0;
        int sum = 0;
        int digitcount = 0;
        if(isReverse)
        {
            while(s != null)
            {
                sum += s.data * Math.pow(10,digitcount);
                digitcount ++;
                s = s.next;
            }
        }
        else
        {
            while(s != null)
            {
                sum = sum * 10 + s.data;
                s = s.next;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LinkedListNode lA1 = new LinkedListNode(9, null, null);
        LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
        LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

        LinkedListNode lB1 = new LinkedListNode(1, null, null);
        LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(0, null, lB2);

        LinkedListNode list3 = addLists(lA1, lB1);

        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + list3.printForward());

        int l1 = ListToInt(lA1, true);
        int l2 = ListToInt(lB1, true);
        int l3 = ListToInt(list3,true);

        System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
        System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));
    }
}
