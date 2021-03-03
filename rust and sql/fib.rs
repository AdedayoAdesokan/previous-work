//Calculates the Fibonacci number at index n
fn calculate_fib(n: u32) -> u128 {  
    let five: f64 = 5.0;  //initialize five to 5
    let value1 = 1.0 + five.powf(0.5);  //Calculate the 1st value
    let term1 = value1.powf(n as f64);  //Calculate the first term in the numberator

    let value2 = 1.0 - five.powf(0.5);  //Calculate the 2nd value
    let term2 = value2.powf(n as f64);  //Calculate the second term in the numberator

    let two: f64 = 2.0;  //Initialaize two to 2
    let divisor = two.powf(n as f64) * five.powf(0.5);  //Calculate the divisor
    let numerator = term1 - term2;  //Calculate the numerator
    let asnwer: f64 = numerator / divisor;  //Calculate the answer
    asnwer as u128  //Return it as an integer
}

//Prints the first 50 Fibonacci numbers
fn fib_printer() {  
    for x in 0..51 {  //Iterate from 0 to 50
        let fib_value = calculate_fib(x);  //Calculate the Fibonacci number at index x
        println!("{}", fib_value);  //Print the number
    }
}

fn main() {
    fib_printer();  //Run the function 
}
