//
//  ComposeScreen.swift
//  iosApp
//
//  Created by Rafsanjani Abdul-Aziz on 13/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import shared
import Foundation
import SwiftUI

struct MainScreen: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ViewControllers.shared.mainViewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
}
