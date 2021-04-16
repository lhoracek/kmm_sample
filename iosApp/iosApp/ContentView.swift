import SwiftUI
import shared

struct ContentView: View {
    let calculator = Calculator.Companion()
    let greet = Greeting().greeting()
    
    
    @State private var firstNum: String = "0"
    @State private var secondNum: String = "0"
    private var sum: String {
        if let firstNum = Int32(firstNum), let secondNum = Int32(secondNum) {
            return String(calculator.sum(a: firstNum, b: secondNum))
        } else {
            return "🤔"
        }
    }
    
    @State private var number: String = "0"

    @State private var called: Bool = false

     func register() {
        if(!called){
            let x = Greeting().greetingFlowWrapped()
            x.watch {(newNumber) in
                self.number = newNumber?.stringValue ?? ""
                print("Hello " + (newNumber?.stringValue ?? ""))
            }
            self.setCalled(cal: true)
        }
    }
    
     func setCalled(cal : Bool){
        called = cal
    }
    
    
    var body: some View {
        register()
        return VStack(alignment: .center) {
            Text(greet)
            HStack(alignment: .center) {
                TextField("A", text: $firstNum)
                    .keyboardType(.numberPad)
                    .multilineTextAlignment(.center)
                    .frame(width: 30)
                Text("+")
                TextField("B", text: $secondNum)
                    .keyboardType(.numberPad)
                    .multilineTextAlignment(.center)
                    .frame(width: 30)
                Text("=")
                Text(sum)
            
            }
            Text(number)
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
