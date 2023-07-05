Blog API
This is a simple RESTful API for a blog application that allows you to perform CRUD operations on blog posts.

How to Run the Project
Clone the repository to your local machine.
Navigate to the project directory.
Make sure you have Java Development Kit (JDK) 8 or higher and Maven installed on your system.
Build the project using Maven.
Run the application.
The application will start running on http://localhost:8080.

API Endpoints
Create a Blog Post
URL: /posts
Method: POST
Request Body:
json
Copy code
{
"title": "Example Title",
"content": "Example Content",
"author": "John Doe"
}
Get All Blog Posts
URL: /posts
Method: GET
Get a Blog Post by ID
URL: /posts/{id}
Method: GET
Update a Blog Post
URL: /posts/{id}
Method: PUT
Request Body:
json
Copy code
{
"title": "Updated Title",
"content": "Updated Content",
"author": "Jane Doe"
}
Delete a Blog Post
URL: /posts/{id}
Method: DELETE
Testing with Postman
To test the application, you can use Postman. Follow these steps:

Download and install Postman on your computer if you haven't already.
Open Postman and import the provided collection.
Use the following examples to test each CRUD operation using Postman.
GET /posts: Retrieve all blog posts

Method: GET
URL: http://localhost:8080/posts
Expected Result: You should receive a response with a list of all blog posts.
GET /posts/{id}: Retrieve a specific blog post by ID

Method: GET
URL: http://localhost:8080/posts/{id} (replace {id} with the actual ID of a blog post)
Expected Result: You should receive a response with the details of the specified blog post.
POST /posts: Create a new blog post

Method: POST
URL: http://localhost:8080/posts
Request Body (JSON):
json
Copy code
{
"title": "New Blog Post",
"content": "This is the content of the new blog post.",
"author": "Mr Publicator"
}
Expected Result: You should receive a response with the details of the newly created blog post.
PUT /posts/{id}: Update an existing blog post

Method: PUT
URL: http://localhost:8080/posts/{id} (replace {id} with the actual ID of the blog post to update)
Request Body (JSON):
json
Copy code
{
"title": "Updated Blog Post",
"content": "This is the updated content of the blog post.",
"author": "Jane Smith"
}
Expected Result: You should receive a response with the updated details of the blog post. If you try to update a post that doesn't exist, you will receive the message: "Blog post not found with id: " + id.
DELETE /posts/{id}: Delete ablog post

Method: DELETE
URL: http://localhost:8080/posts/{id} (replace {id} with the actual ID of the blog post to delete)
Expected Result: You should receive a response with a 204 No Content status, and the message "Blog post deleted successfully". If you try to delete a post that doesn't exist, you will receive the message: "Blog post not found with id: " + id.

Conclusion

You now have a basic understanding of how to run and test the Blog API using Postman. With the provided Postman collection and the information in this README, you can easily interact with the API endpoints and perform CRUD operations on the blog posts.