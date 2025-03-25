import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions,
  MenuItem, Select, InputLabel, FormControl, Rating
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import {
  getReviews, createReview, updateReview, deleteReview,
  getBooks, getUsers
} from '../services/api';

const ReviewsPage = () => {
  const [reviews, setReviews] = useState([]);
  const [books, setBooks] = useState([]);
  const [users, setUsers] = useState([]);
  const [currentReview, setCurrentReview] = useState({
    bookId: '',
    userId: '',
    rating: 3,
    comment: ''
  });
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchReviews();
    fetchBooks();
    fetchUsers();
  }, []);

  const fetchReviews = async () => {
    try {
      const response = await getReviews();
      setReviews(response.data);
    } catch (error) {
      console.error('Error fetching reviews:', error);
    }
  };

  const fetchBooks = async () => {
    try {
      const response = await getBooks();
      setBooks(response.data);
    } catch (error) {
      console.error('Error fetching books:', error);
    }
  };

  const fetchUsers = async () => {
    try {
      const response = await getUsers();
      setUsers(response.data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  const handleAddReview = async () => {
    if (!currentReview.bookId || !currentReview.userId) return;

    try {
      const reviewData = {
        bookId: parseInt(currentReview.bookId),
        userId: parseInt(currentReview.userId),
        rating: parseInt(currentReview.rating),
        comment: currentReview.comment || null
      };
      await createReview(reviewData);
      resetForm();
      fetchReviews();
    } catch (error) {
      console.error('Error adding review:', error);
    }
  };

  const handleUpdateReview = async () => {
    if (!currentReview.bookId || !currentReview.userId) return;

    try {
      const reviewData = {
        bookId: parseInt(currentReview.bookId),
        userId: parseInt(currentReview.userId),
        rating: parseInt(currentReview.rating),
        comment: currentReview.comment || null
      };
      await updateReview(currentReview.id, reviewData);
      resetForm();
      fetchReviews();
    } catch (error) {
      console.error('Error updating review:', error);
    }
  };

  const handleDeleteReview = async (id) => {
    try {
      await deleteReview(id);
      fetchReviews();
    } catch (error) {
      console.error('Error deleting review:', error);
    }
  };

  const resetForm = () => {
    setCurrentReview({
      bookId: '',
      userId: '',
      rating: 3,
      comment: ''
    });
    setOpenDialog(false);
    setIsEditing(false);
  };

  const getBookTitleById = (bookId) => {
    const book = books.find(b => b.id === bookId);
    return book ? book.title : '-';
  };

  const getUsernameById = (userId) => {
    const user = users.find(u => u.id === userId);
    return user ? user.username : '-';
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Reviews</Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
          onClick={() => {
            setCurrentReview({
              bookId: '',
              userId: '',
              rating: 3,
              comment: ''
            });
            setIsEditing(false);
            setOpenDialog(true);
          }}
        >
          Add Review
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Book</TableCell>
              <TableCell>User</TableCell>
              <TableCell>Rating</TableCell>
              <TableCell>Comment</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {reviews.map((review) => (
              <TableRow key={review.id}>
                <TableCell>{review.id}</TableCell>
                <TableCell>{review.book?.title || getBookTitleById(review.book?.id)}</TableCell>
                <TableCell>{review.user?.username || getUsernameById(review.user?.id)}</TableCell>
                <TableCell>
                  <Rating value={review.rating} readOnly />
                </TableCell>
                <TableCell>{review.comment || '-'}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setCurrentReview({
                      id: review.id,
                      bookId: review.book?.id || '',
                      userId: review.user?.id || '',
                      rating: review.rating,
                      comment: review.comment || ''
                    });
                    setIsEditing(true);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteReview(review.id)}>
                    <Delete color="error" />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Add/Edit Dialog */}
      <Dialog open={openDialog} onClose={resetForm} fullWidth maxWidth="sm">
        <DialogTitle>{isEditing ? 'Edit Review' : 'Add New Review'}</DialogTitle>
        <DialogContent sx={{ pt: 2, '& > *': { my: 1 } }}>
          <FormControl fullWidth>
            <InputLabel>Book *</InputLabel>
            <Select
              value={currentReview.bookId}
              label="Book *"
              onChange={(e) => setCurrentReview({
                ...currentReview,
                bookId: e.target.value
              })}
            >
              {books.map((book) => (
                <MenuItem key={book.id} value={book.id}>
                  {book.title}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth>
            <InputLabel>User *</InputLabel>
            <Select
              value={currentReview.userId}
              label="User *"
              onChange={(e) => setCurrentReview({
                ...currentReview,
                userId: e.target.value
              })}
            >
              {users.map((user) => (
                <MenuItem key={user.id} value={user.id}>
                  {user.username}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <Box>
            <Typography component="legend">Rating</Typography>
            <Rating
              value={currentReview.rating}
              onChange={(e, newValue) => {
                setCurrentReview({
                  ...currentReview,
                  rating: newValue
                });
              }}
            />
          </Box>
          <TextField
            fullWidth
            multiline
            rows={3}
            label="Comment"
            value={currentReview.comment}
            onChange={(e) => setCurrentReview({
              ...currentReview,
              comment: e.target.value
            })}
            inputProps={{ maxLength: 1000 }}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={resetForm}>Cancel</Button>
          <Button
            onClick={isEditing ? handleUpdateReview : handleAddReview}
            color="primary"
            variant="contained"
            disabled={!currentReview.bookId || !currentReview.userId}
          >
            {isEditing ? 'Update' : 'Save'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default ReviewsPage;