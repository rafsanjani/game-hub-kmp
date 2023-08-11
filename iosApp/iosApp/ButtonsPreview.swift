import SwiftUI
import Foundation
import shared

struct ComposeButton : UIViewControllerRepresentable{
    let text: String
    func makeUIViewController(context: Context) -> some UIViewController {
        ViewControllers.shared.sampleButtonController(text: text) {
            print("Hello from Compose Button")
        }
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}

struct ButtonsPreview : View {
    var body: some View {
           VStack {
               ComposeButton(text: "Real time button")
                   .offset(y: 30)
           }
       }
}

struct Preview: PreviewProvider {
    static var previews: some View {
        ButtonsPreview()
    }
}
