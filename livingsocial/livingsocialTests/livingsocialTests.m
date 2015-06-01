//
//  livingsocialTests.m
//  livingsocialTests
//
//  Created by Andres Cano on 6/1/15.
//  Copyright (c) 2015 Livecano. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <XCTest/XCTest.h>

#import "NSDictionary+merchant.h"

@interface livingsocialTests : XCTestCase

@property (nonatomic, strong) NSDictionary *merchant;

@end

@implementation livingsocialTests

- (void)setUp {

  [super setUp];

  // Put setup code here.
  // This method is called before the invocation of each test method in the class.
  
  // Setting up an example of the information of a merchant
  
  _merchant = @{
    @"description": @"Rem id debitis pariatur a ut. Quod et excepturi. Vero repudiandae harum aut iste. Quia sit animi perferendis earum ea. Nostrum perspiciatis tempore fugiat non aut eius. Delectus voluptatem cumque. Sit pariatur expedita reprehenderit deleniti sit nemo ad. Sapiente assumenda ea quos dignissimos. Et velit voluptatum. Et sed suscipit modi quia. Reprehenderit voluptates repudiandae. Omnis autem et.",
    @"image_url": @"http://robohash.org/occaecati-aut.png?size=500x500",
    @"merchant_name": @"MacGyver, Shanahan and Hickle",
    @"price": @"60.789999999999999",
    @"title": @"Intelligent Steel Gloves"
    };
  
}

- (void)tearDown {
  
  _merchant = nil;
  
  // Put teardown code here.
  // This method is called after the invocation of each test method in the class.
  
  [super tearDown];
  
}


// Set of 4 test to evaluate availablity of methods to retrieve information of
// a merchant

- (void)testMerchantNameAvailability {
  
  XCTAssertTrue([NSDictionary instancesRespondToSelector:@selector(merchantName)],
                @"merchantName method does not exist on NSDictionary class");
}

- (void)testMerchantDescriptionAvailability {
  
  XCTAssertTrue([NSDictionary instancesRespondToSelector:@selector(merchantDescription)],
                @"merchantName method does not exist on NSDictionary class");
}

- (void)testMerchantPriceAvailability {
  
  XCTAssertTrue([NSDictionary instancesRespondToSelector:@selector(merchantPrice)],
                @"merchantName method does not exist on NSDictionary class");
}

- (void)testMerchantTitleAvailability {
  
  XCTAssertTrue([NSDictionary instancesRespondToSelector:@selector(merchantTitle)],
                @"merchantName method does not exist on NSDictionary class");
}

// Test e.g. disccounted price availability
// NOTE: It should fail, there is no disccounted price method for a merchant saved
// on a NSDictionary

- (void)testMerchantDisccountedPriceAvailability {
  
  XCTAssertTrue([NSDictionary instancesRespondToSelector:@selector(merchantDisccountedPrice)],
                @"merchantName method does not exist on NSDictionary class");
}

@end
