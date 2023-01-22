package unitTesting_w4

def add(x: Int, y: Int): Int = x + y

def fibonacci(n: Int): Int = 
  n match
    case 0 => 0
    case 1 => 1
    case _ => fibonacci(n-2) + fibonacci(n-1)