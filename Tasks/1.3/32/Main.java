public class Main
{
    public static void main(String[] args)
    {
        StackQueue<Integer> stackQueue = new StackQueue<>();

        stackQueue.push(1);
        stackQueue.push(4);

        System.out.println(stackQueue.pop());

        stackQueue.append(3);
        stackQueue.append(2);

        System.out.println(stackQueue.pop());
        System.out.println(stackQueue.peek());
        System.out.println(stackQueue.isEmpty());
    }
}