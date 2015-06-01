//
//  NSDictionary+merchant.h
//  livingsocial
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

@interface NSDictionary (merchant)

- (NSString *)merchantName;
- (NSString *)merchantTitle;
- (NSString *)merchantDescription;
- (NSURL *)merchantImageURL;
- (NSNumber *)merchantPrice;

@end
