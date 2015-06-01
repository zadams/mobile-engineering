//
//  NSDictionary+merchant.m
//  livingsocial
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import "NSDictionary+merchant.h"

@implementation NSDictionary (merchant)

- (NSString *)merchantName {
  return self[@"merchant_name"];
}

- (NSString *)merchantTitle {
  return self[@"title"];
}

- (NSString *)merchantDescription {
  return self[@"description"];
}

- (NSURL *)merchantImageURL {
  NSString *imageURLString = self[@"image_url"];
  NSURL *imageURL = [[NSURL alloc] initWithString:imageURLString];
  return imageURL;
}

- (NSNumber *)merchantPrice {
  NSString *cc = self[@"price"];
  NSNumber *n = @([cc floatValue]);
  return n;
}

@end
