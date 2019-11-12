import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.Random;

public class TestArrayDequeGold {

    private int numOfChoices = 4;

    private int[] getFrequencyArray(int i){
        int[] frequencyArray = new int[i];
        for(int j=0; j<frequencyArray.length; j++){
            frequencyArray[j]=1;
        }
        return frequencyArray;
    }

    @Test
    public void testArray(){
        StudentArrayDeque<Integer> studentArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionArray = new ArrayDequeSolution<>();
        String calls = new String();
        int[] frequencyArray = getFrequencyArray(numOfChoices); //[1,1,1,1]
        //System.out.println(frequencyArray);

        Integer i=0;
        //for (i=0; i<100; i++){
        while(true){
            Integer removeFromStudent=0;
            Integer removeFromSolution=0;
            boolean remove=false;
            int tempChoice = StdRandom.discrete(frequencyArray);

            switch (tempChoice){
                case 0: //addFirst
                    studentArray.addFirst(i);
                    solutionArray.addFirst(i);
                    calls+=("addFirst("+i.toString()+")\n");
                    break;
                case 1://addLast
                    studentArray.addLast(i);
                    solutionArray.addLast(i);
                    calls+="addLast("+i.toString()+")\n";

                    break;
                case 2://removeFirst
                    if (!studentArray.isEmpty() && !solutionArray.isEmpty()){
                        removeFromStudent = studentArray.removeFirst();
                        removeFromSolution = solutionArray.removeFirst();
                        remove=true;
                        calls+="removeFirst()\n";
                        //calls+= removeFromSolution.toString()+" ";
                        //calls+= removeFromStudent.toString()+"\n";
                    }
                    break;
                case 3://removeLast
                    if (!studentArray.isEmpty() && !solutionArray.isEmpty()){
                        removeFromStudent = studentArray.removeLast();
                        removeFromSolution = solutionArray.removeLast();
                        remove=true;
                        calls+="removeLast()\n";
                        //calls+= removeFromSolution.toString()+" ";
                        //calls+= removeFromStudent.toString()+"\n";
                    }
                    break;
            }
            if((tempChoice==2 || tempChoice==3) && remove){ //remove action
                //System.out.print(removeFromSolution);
                //System.out.print(removeFromStudent);

                assertEquals(calls, removeFromSolution, removeFromStudent);
            }
            i+=1;

        }
    }



}
