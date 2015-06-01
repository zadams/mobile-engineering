//
//  MainTableViewController.h
//  livingsocial
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MainTableViewController : UITableViewController <UISearchControllerDelegate>

// Actions
- (IBAction)clear:(id)sender;
- (IBAction)loadJSONTapped:(id)sender;

@end
