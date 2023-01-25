# PROBLEM DOMAIN
The main purpose of the system is to create an environment to represent and sell textile products. 

# SPECIFICATIONS
The operation of the system starts with the seller adding the products to the system, and then the products are offered for sale to the customer. After logging in to the system , the customer can review the listed products as he wishes , add them to his favourites , and add them to the cart . If the user wants to create an order, he has to go to the system. The user who does not have a membership in the system is directed to the registration page and after the registration is completed, he can log in to the system and continue the order process. The customer enters the delivery address and payment information into the system. Accordingly, the user packs the products and delivers them to the relevant cargo company. The cargo company delivers the product to the customer. If the customer who receives the product likes the product, he continues to use it, if he does not like it, he creates a return request and returns the product and gets his money back.

# USED TECHNOLOGY TOOLS AND PROGRAMMING LANGUAGES

Technologies:
* Microservices architecture
* Frameworks (e.g. Spring, React)

Tools:
* Version control system (e.g. Git)
* Project management (e.g. notion.so)

Programming Languages:
* HTML, CSS, JavaScript (front-end development)
* JAVA - Spring Framework (back-end development)
* PostgreSQL (database management)
* API design and development (e.g. Swagger)

# ENTITIES

There will be totaly 20 database tables for "Textile E-Commerce App".It is details will be bottom as a table name and their attributes.

## admins

| admin_id  | date_of_register | email | first_name | last_name  | password |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | 

## baskets

| customer_id  | item_id |
| ------------- | ------------- |

## categories

| category_id  | category_name |
| ------------- | ------------- |

## colors

| color_id  | color_name |
| ------------- | ------------- |

## customer_locations

| customer_id  | location_id |
| ------------- | ------------- |

## customers

| customer_id  | date_of_register | email | first_name | last_name  | password | identity_number  | is_active | phone_number | last_location_id | budget | 
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |

## discount_codes

| discount_code_id  | discount_code_amount | discount_code | discount_price | 
| ------------- | ------------- | ------------- | ------------- |

## favorites

| product_id  | customer_id |
| ------------- | ------------- |

## images

| images  | img_url | product_id |
| ------------- | ------------- | ------------- |

## items

| item_id  | amount | product_id | size_id |
| ------------- | ------------- | ------------- | ------------- |

## locations

| location_id  | address | city | post_code | location_title  | town |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | 

## logs

| log_id  | date | content |
| ------------- | ------------- | ------------- |

## order_detail

| order_detail_id  | amount | order_id | product_id | size_id  | is_refunded | is_canceled |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |

## orders

| order_id  | creation_date | total_price | billing_location_id | customer_id  | discount_code_id | location_id  | cancel_date | is_canceled |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |

## product_sizes

| product_id  | size_id |
| ------------- | ------------- |

## products

| product_id  | discount_percentage | gender | name | price  | category_id | color_id | typology_id |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |

## refunds

| refund_id  | is_online | refund_date | customer_id | order_detail_id  | cause |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | 

## sizes

| size_id  | size_name |
| ------------- | ------------- |

## stocks

| stock_id  | count | product_id | size_id |
| ------------- | ------------- | ------------- | ------------- |

## typologies

| typology_id  | gender | typology_name | category_id |
| ------------- | ------------- | ------------- | ------------- |
