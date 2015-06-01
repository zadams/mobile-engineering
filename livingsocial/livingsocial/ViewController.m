//
//  ViewController.m
//  livingsocial
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import "ViewController.h"

#import "NSDictionary+merchant.h"

#import "AFNetworking.h"
#import "UIImageView+AFNetworking.h"

@interface ViewController ()

@property (nonatomic, weak) IBOutlet UILabel *nameMerchant;
@property (nonatomic, weak) IBOutlet UILabel *titleMerchant;
@property (nonatomic, weak) IBOutlet UILabel *priceMerchant;
@property (nonatomic, weak) IBOutlet UIImageView *imageMerchant;
@property (nonatomic, weak) IBOutlet UITextView *descriptionMerchant;

// additional properties for scroll view, auto layout workaround
@property (nonatomic, weak) IBOutlet UIScrollView *scrollView;
@property (nonatomic, weak) IBOutlet UIView *contentView;

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any additional setup after loading the view, typically from a nib.
  
  // load name merchant
  self.nameMerchant.text = [self.merchant merchantName];
  
  // load title merchant
  self.titleMerchant.text = [self.merchant merchantTitle];
  
  // load price merchant
  self.priceMerchant.text = [NSString stringWithFormat:@"Price: $%@",[[self.merchant merchantPrice] stringValue]];
  
  // load description merchant
  self.descriptionMerchant.text = [self.merchant merchantDescription];
  [self.descriptionMerchant sizeToFit];
  
  NSURLRequest *request = [NSURLRequest requestWithURL:[self.merchant merchantImageURL]];
  UIImage *placeholderImage = [UIImage imageWithContentsOfFile:@"Placeholder"];
  
  __weak UIImageView *weakImageMerchant = self.imageMerchant;
  
  [weakImageMerchant setImageWithURLRequest:request
                        placeholderImage:placeholderImage
                                 success:^(NSURLRequest *request, NSHTTPURLResponse *response, UIImage *image) {
                                   
                                   weakImageMerchant.image = image;
                                   [weakImageMerchant setNeedsLayout];
                                   
                                 } failure:nil];
  
  // scroll view content view additional constrains
  
  // Set the translatesAutoresizingMaskIntoConstraints to NO
  // so that the views autoresizing mask is not translated into auto layout constraints.
  
  //self.scrollView.translatesAutoresizingMaskIntoConstraints  = NO;
  self.contentView.translatesAutoresizingMaskIntoConstraints = NO;
  
  
  NSLayoutConstraint *leftConstraint = [NSLayoutConstraint constraintWithItem:self.contentView
                                                                    attribute:NSLayoutAttributeLeading
                                                                    relatedBy:0
                                                                       toItem:self.view
                                                                    attribute:NSLayoutAttributeLeft
                                                                   multiplier:1.0
                                                                     constant:0];
  [self.view addConstraint:leftConstraint];
  
  NSLayoutConstraint *rightConstraint = [NSLayoutConstraint constraintWithItem:self.contentView
                                                                     attribute:NSLayoutAttributeTrailing
                                                                     relatedBy:0
                                                                        toItem:self.view
                                                                     attribute:NSLayoutAttributeRight
                                                                    multiplier:1.0
                                                                      constant:0];
  [self.view addConstraint:rightConstraint];
  
  NSLayoutConstraint *bottomConstraint = [NSLayoutConstraint constraintWithItem:self.contentView
                                                                     attribute:NSLayoutAttributeBottomMargin
                                                                     relatedBy:0
                                                                        toItem:self.view
                                                                     attribute:NSLayoutAttributeBottomMargin
                                                                    multiplier:1.0
                                                                      constant:0];
  [self.view addConstraint:bottomConstraint];
  
}

- (void)dealloc {
  self.merchant = nil;
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
