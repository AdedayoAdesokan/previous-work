//Gets the index of the partition given a list of numbers
fn get_partition(list: &mut Vec<isize>, low: usize, high: usize) -> usize {
    let pivot = list[high] as isize; //Set pivot as the last element in the list
    let mut i: isize = low as isize - 1; //The index of an element less than the pivot
    for j in low..high {
        //Iterate through the list
        if list[j] < pivot {
            //If the elemnent at index j is less than the pivot
            i += 1; //Increment i
            let holder = list[i as usize]; //Store the element at index i
            list[i as usize] = list[j]; //Set the element at i = the the element at index j
            list[j] = holder; //Set the element at index j to the stored element
        }
    }
    let index = i + 1; //Increment i
    let substitute = list[index as usize]; //Store the elemnt at index i+1
    list[index as usize] = list[high]; //Place the pivot in the right position
    list[high] = substitute; //Set the last element in the list to the stored value

    return index as usize; //Return the index of the piviot
}

//Sorts the given list with the quicksort algorithm
fn quick_sort(list: &mut Vec<isize>, low: usize, high: usize) {
    if low < high {
        //Stop when the list is sorted
        let partition_index: usize = get_partition(list, low, high); //Get the index of the partition

        if partition_index > 0 {
            //If the partition index is not equal to zero
            quick_sort(list, low, partition_index - 1); //Quick sort the elements less than the partition
            quick_sort(list, partition_index + 1, high); //Quick sort the elements greater than the partition
        } else {
            quick_sort(list, partition_index + 1, high); //Quick sort the elements greater than the partition
        }
    }
}

//Prints the given list
fn print_list(list: &mut Vec<isize>, n: usize) {
    print!("["); //Print the openining bracket
    for i in 0..n {
        //Iterate through the list
        if i != n - 1 {
            //If i is not at the last index in the list
            print!("{}, ", list[i]); //Print the number with a comma
        } else {
            print!("{}", list[i]); //Print the number without a comma
        }
    }
    print!("]"); //Print the closing brackt
    println!(); //Skip a line
}

use std::io::{self, BufRead};

fn main() {
    println!("please input a single list of numbers seperated by spaces: "); //Prompt the user to inupt a list of numbers

    let stdin = io::stdin();
    let input = stdin.lock().lines().next().unwrap().unwrap(); //Get the input from standard input
    let values = input.split_whitespace(); //Spit the input by white space
    let mut vec = Vec::new(); //Initialize a vector
    for x in values {
        //Iterate through the input
        let num: isize = x.parse().unwrap(); //Convert the string input to a number
        vec.push(num); //Add the number to the vector
    }
    let n = vec.len(); //Get the length of the vector
    quick_sort(&mut vec, 0, n - 1); //Sort the list
    print!("Sorted List: "); //Print the sorted list
    print_list(&mut vec, n); //Print the sorted list
}
