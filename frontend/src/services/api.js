import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api'; // Замените на ваш URL

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Genres API
export const getGenres = () => api.get('/genres');
export const createGenre = (name) => api.post('/genres', { name });
export const updateGenre = (id, name) => api.put(`/genres/${id}`, { name });
export const deleteGenre = (id) => api.delete(`/genres/${id}`);

export const getAuthors = () => api.get('/authors');
export const getAuthorById = (id) => api.get(`/authors/${id}`);
export const createAuthor = (authorData) => api.post('/authors', authorData);
export const updateAuthor = (id, authorData) => api.put(`/authors/${id}`, authorData);
export const deleteAuthor = (id) => api.delete(`/authors/${id}`);

// Roles API
export const getRoles = () => api.get('/roles');
export const getRoleById = (id) => api.get(`/roles/${id}`);
export const createRole = (roleData) => api.post('/roles', { roleName: roleData.roleName });
export const updateRole = (id, roleData) => api.put(`/roles/${id}`, { roleName: roleData.roleName });
export const deleteRole = (id) => api.delete(`/roles/${id}`);

// Users API
export const getUsers = () => api.get('/users');
export const getUserById = (id) => api.get(`/users/${id}`);
export const createUser = (userData) => api.post('/users', userData);
export const updateUser = (id, userData) => api.put(`/users/${id}`, userData);
export const deleteUser = (id) => api.delete(`/users/${id}`);

export const getBooks = () => api.get('/books');
export const getBookById = (id) => api.get(`/books/${id}`);
export const createBook = (bookData) => api.post('/books', bookData);
export const updateBook = (id, bookData) => api.put(`/books/${id}`, bookData);
export const deleteBook = (id) => api.delete(`/books/${id}`);

export const getReviews = () => api.get('/reviews');
export const getReviewById = (id) => api.get(`/reviews/${id}`);
export const createReview = (reviewData) => api.post('/reviews', reviewData);
export const updateReview = (id, reviewData) => api.put(`/reviews/${id}`, reviewData);
export const deleteReview = (id) => api.delete(`/reviews/${id}`);

export const getIssues = () => api.get('/issues');
export const getIssueById = (id) => api.get(`/issues/${id}`);
export const createIssue = (issueData) => api.post('/issues', issueData);
export const updateIssue = (id, issueData) => api.put(`/issues/${id}`, issueData);
export const deleteIssue = (id) => api.delete(`/issues/${id}`);

export default api;