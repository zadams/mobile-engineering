//
//  MainTableViewController.m
//  livingsocial
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import "MainTableViewController.h"

#import "ViewController.h"
#import "NSDictionary+merchant.h"

#import "AFNetworking.h"
#import "UIImageView+AFNetworking.h"

static NSString * const BaseURLString = @"http://sheltered-bastion-2512.herokuapp.com/";

@interface MainTableViewController ()

@property (nonatomic, strong) NSArray *merchants;
@property (nonatomic, strong) NSDictionary *selectedMerchant;

// use for search
@property (nonatomic, strong) NSArray *filteredMerchants;

@end

@implementation MainTableViewController

- (void)viewDidLoad {
  
  [super viewDidLoad];
  
  // load JSON as soon as view is initialized
  [self loadJSONTapped:nil];

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
  
  
  if (tableView == self.tableView) {
    
    if(!self.merchants)
      return 0;
    
    return [self.merchants count];

  } else {
    
    if(!self.filteredMerchants)
      return 0;
    
    return [self.filteredMerchants count];

  }
  
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  
  if (tableView == self.tableView) {
  
    static NSString *CellIdentifier = @"MerchantCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier
                                                            forIndexPath:indexPath];
    
    NSDictionary *merchant = nil;
    merchant = self.merchants[indexPath.row];
    
    cell.textLabel.text = [merchant merchantTitle];
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[merchant merchantImageURL]];
    UIImage *placeholderImage = [UIImage imageNamed:@"Placeholder"];
    
    __weak UITableViewCell *weakCell = cell;
    
    [cell.imageView setImageWithURLRequest:request
                          placeholderImage:placeholderImage
                                   success:^(NSURLRequest *request, NSHTTPURLResponse *response, UIImage *image) {
                                     
                                     weakCell.imageView.image = image;
                                     [weakCell setNeedsLayout];
                                     
                                   } failure:nil];
    
    return cell;
    
  } else {
    
    static NSString *kCellID = @"SearchResultsCell";
    // Dequeue a cell from self's table view.
    UITableViewCell *cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle
                                                   reuseIdentifier:kCellID];
    
    [cell setAccessoryType:UITableViewCellAccessoryDisclosureIndicator];
    
    NSDictionary *filteredMerchant = nil;
    filteredMerchant = self.filteredMerchants[indexPath.row];
    
    cell.textLabel.text = [filteredMerchant merchantTitle];
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[filteredMerchant merchantImageURL]];
    UIImage *placeholderImage = [UIImage imageNamed:@"Placeholder"];
    
    __weak UITableViewCell *weakCell = cell;
    
    [cell.imageView setImageWithURLRequest:request
                          placeholderImage:placeholderImage
                                   success:^(NSURLRequest *request, NSHTTPURLResponse *response, UIImage *image) {
                                     
                                     weakCell.imageView.image = image;
                                     [weakCell setNeedsLayout];
                                     
                                   } failure:nil];
    
    return cell;
    
  }
  
  
}

- (void)tableView:(UITableView *)tableView
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  
  if (tableView == self.tableView) {
  
    self.selectedMerchant = [self.merchants objectAtIndex:indexPath.row];
    
  } else {
    
    self.selectedMerchant = [self.filteredMerchants objectAtIndex:indexPath.row];
    
  }
  
    [self performSegueWithIdentifier:@"showDetailProductMerchant" sender:self];
  
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
  
  ViewController *detailViewController = [segue destinationViewController];
  detailViewController.merchant = self.selectedMerchant;

}


#pragma mark - Actions

- (IBAction)clear:(id)sender
{
  //self.title = @"";
  self.merchants = nil;
  [self.tableView reloadData];
}

- (IBAction)loadJSONTapped:(id)sender
{
  
  NSString *string = [NSString stringWithFormat:@"%@feed.json", BaseURLString];
  NSURL *url = [NSURL URLWithString:string];
  NSURLRequest *request = [NSURLRequest requestWithURL:url];
  
  AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc] initWithRequest:request];
  operation.responseSerializer = [AFJSONResponseSerializer serializer];
  
  __weak id weakSelf = self;
  
  [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
    
    [weakSelf parseJSON:responseObject];
    [[weakSelf tableView] reloadData];
    
  } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
    
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Error Retrieving Merchant Data"
                                                        message:[NSString stringWithFormat:@"%@ - \n%@",
                                                                 [error localizedDescription],
                                                                 @"Please Try Reload Again!"]
                                                       delegate:nil
                                              cancelButtonTitle:@"Ok"
                                              otherButtonTitles:nil];
    [alertView show];

  }];

  [operation start];
}


#pragma mark - Parse JSON

- (void)parseJSON:(id)JSON {
  self.merchants = (NSArray *)JSON;
  //self.title = @"Loaded!";
}


#pragma mark - Search Delegate Methods

- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar {
  
}

- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar {
  
  //[searchBar resignFirstResponder];
  
}

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"
- (void)searchBar:(UISearchBar *)searchBar
    textDidChange:(NSString *)searchText {
  
  //NSLog(@"search input: %@", searchText);
  
  NSPredicate *predicate = [NSPredicate predicateWithFormat:@"(title CONTAINS[cd] %@ )", searchText];
  self.filteredMerchants = [self.merchants filteredArrayUsingPredicate:predicate];
  
  //NSLog(@"%@",self.filteredMerchants);
  
  [self.searchDisplayController.searchResultsTableView reloadData];

  
}
#pragma clang diagnostic pop

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar {
  
}

- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar {
  
  self.filteredMerchants = self.merchants;
  
}

- (void)updateSearchResultsForSearchController:(UISearchController *)searchController {
  
}

#pragma mark - Selected Scope

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"
- (void)searchBar:(UISearchBar *)searchBar
selectedScopeButtonIndexDidChange:(NSInteger)selectedScope {
  [self.searchDisplayController.searchResultsTableView reloadData];
}
#pragma clang diagnostic pop

@end
