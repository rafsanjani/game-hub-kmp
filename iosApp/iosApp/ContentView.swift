import SwiftUI
import Foundation

struct ContentView : View {
    var body: some View {
        MainScreen()
            .ignoresSafeArea(.all)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
