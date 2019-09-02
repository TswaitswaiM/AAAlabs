import java.util.Random;

public class BubbleSort{



  public static int[] arraySize(int[] array){
        Random randomNumber = new Random();

        for(int i =0;i<array.length;i++){
          array[i]= randomNumber.nextInt();

        }

       return array;

  }


  public static int bubbleSort(int[] array)
  {


    
     int index;       // Index of an element to compare
     int temp;        // Used to swap to elements
     //int countSwaps = 0;
     int countComparisons = 0;
     boolean swapped = true;


     while (swapped == true)
     {
        swapped = false;
        for (index = 0; index < array.length - 1; index++)
        {
           countComparisons++;
           // Compare an element with its neighbor.
           if (array[index] > array[index + 1])
           {
            //  countSwaps++;
              // Swap the two elements.
              temp = array[index];
              array[index] = array[index + 1];
              array[index + 1] = temp;
              swapped = true;
           }

        }
     }



   
  return countComparisons;

  }

   public static void main(String[] args)
   {


     // creating Random object
      System.out.println("size,comparisons");
		  for (int size = 10000;size <= 100000; size = size + 10000) {
            int[] array = new int[size];
            arraySize(array);


            System.out.println(size + "," + bubbleSort(array));


      }






   }



}
