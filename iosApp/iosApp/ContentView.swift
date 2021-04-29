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

     func register() {
        
        Greeting().greetingFlowWrapped(number: 1)
            .watch {(response) in
              ...
            }
        
    }
    
    
    
    var body: some View {
        
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
        }.onAppear(){
            register()
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
