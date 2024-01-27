steeps to follow to lauch the app:
- -git clone <repo_url>
 ** the project contains three micro-services(order-service,product-service and inventory-service) along with a discovery server that take in charge registering theses micro-services  and an api gateway that gives thoses services the ability to communicate only by using service name whitout @ip adress & port.
- create inventory service db with the name:inventory-service in mysql server(default port) containing one table (_inventory)
- create order service db with the name:order-service in mysql server(default port) containing two  tables (_orders,_order_line_items)
-  create product service mongodb  with the name:product-service port(27017) containing one  table (_product)
the purpose of this tuto is to test the correct communication between theses three micro service over api gateway using ureka server registration

u launch all micro service at once and u go to postman and ensure that communcation passes without issue.
but before that make sure that ureka server registers theses micro service by opening browser and write  (localhost:8761)
