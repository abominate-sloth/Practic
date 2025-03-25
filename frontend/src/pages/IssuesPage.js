import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions,
  MenuItem, Select, InputLabel, FormControl
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import { DatePicker } from '@mui/x-date-pickers';
import {
  getIssues, createIssue, updateIssue, deleteIssue,
  getBooks, getUsers
} from '../services/api';
import dayjs from 'dayjs';

const IssuesPage = () => {
  const [issues, setIssues] = useState([]);
  const [books, setBooks] = useState([]);
  const [users, setUsers] = useState([]);
  const [currentIssue, setCurrentIssue] = useState({
    bookId: '',
    readerId: '',
    employeeId: '',
    issueDate: dayjs(),
    returnDate: null
  });
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchIssues();
    fetchBooks();
    fetchUsers();
  }, []);

  const fetchIssues = async () => {
    try {
      const response = await getIssues();
      setIssues(response.data);
    } catch (error) {
      console.error('Error fetching issues:', error);
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

  const handleAddIssue = async () => {
    if (!currentIssue.bookId || !currentIssue.readerId || !currentIssue.employeeId) return;

    try {
      const issueData = {
        bookId: parseInt(currentIssue.bookId),
        readerId: parseInt(currentIssue.readerId),
        employeeId: parseInt(currentIssue.employeeId),
        issueDate: currentIssue.issueDate.format('YYYY-MM-DD'),
        returnDate: currentIssue.returnDate ? currentIssue.returnDate.format('YYYY-MM-DD') : null
      };
      await createIssue(issueData);
      resetForm();
      fetchIssues();
    } catch (error) {
      console.error('Error adding issue:', error);
    }
  };

  const handleUpdateIssue = async () => {
    if (!currentIssue.bookId || !currentIssue.readerId || !currentIssue.employeeId) return;

    try {
      const issueData = {
        bookId: parseInt(currentIssue.bookId),
        readerId: parseInt(currentIssue.readerId),
        employeeId: parseInt(currentIssue.employeeId),
        issueDate: currentIssue.issueDate.format('YYYY-MM-DD'),
        returnDate: currentIssue.returnDate ? currentIssue.returnDate.format('YYYY-MM-DD') : null
      };
      await updateIssue(currentIssue.id, issueData);
      resetForm();
      fetchIssues();
    } catch (error) {
      console.error('Error updating issue:', error);
    }
  };

  const handleDeleteIssue = async (id) => {
    try {
      await deleteIssue(id);
      fetchIssues();
    } catch (error) {
      console.error('Error deleting issue:', error);
    }
  };

  const resetForm = () => {
    setCurrentIssue({
      bookId: '',
      readerId: '',
      employeeId: '',
      issueDate: dayjs(),
      returnDate: null
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

  const formatDate = (dateString) => {
    if (!dateString) return '-';
    return dayjs(dateString).format('DD.MM.YYYY');
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Book Issues</Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
          onClick={() => {
            setCurrentIssue({
              bookId: '',
              readerId: '',
              employeeId: '',
              issueDate: dayjs(),
              returnDate: null
            });
            setIsEditing(false);
            setOpenDialog(true);
          }}
        >
          Add Issue
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Book</TableCell>
              <TableCell>Reader</TableCell>
              <TableCell>Employee</TableCell>
              <TableCell>Issue Date</TableCell>
              <TableCell>Return Date</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {issues.map((issue) => (
              <TableRow key={issue.id}>
                <TableCell>{issue.id}</TableCell>
                <TableCell>{issue.book?.title || getBookTitleById(issue.book?.id)}</TableCell>
                <TableCell>{issue.reader?.username || getUsernameById(issue.reader?.id)}</TableCell>
                <TableCell>{issue.employee?.username || getUsernameById(issue.employee?.id)}</TableCell>
                <TableCell>{formatDate(issue.issueDate)}</TableCell>
                <TableCell>{formatDate(issue.returnDate)}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setCurrentIssue({
                      id: issue.id,
                      bookId: issue.book?.id || '',
                      readerId: issue.reader?.id || '',
                      employeeId: issue.employee?.id || '',
                      issueDate: dayjs(issue.issueDate),
                      returnDate: issue.returnDate ? dayjs(issue.returnDate) : null
                    });
                    setIsEditing(true);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteIssue(issue.id)}>
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
        <DialogTitle>{isEditing ? 'Edit Issue' : 'Add New Issue'}</DialogTitle>
        <DialogContent sx={{ pt: 2, '& > *': { my: 1 } }}>
          <FormControl fullWidth>
            <InputLabel>Book *</InputLabel>
            <Select
              value={currentIssue.bookId}
              label="Book *"
              onChange={(e) => setCurrentIssue({
                ...currentIssue,
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
            <InputLabel>Reader *</InputLabel>
            <Select
              value={currentIssue.readerId}
              label="Reader *"
              onChange={(e) => setCurrentIssue({
                ...currentIssue,
                readerId: e.target.value
              })}
            >
              {users.filter(u => u.role?.roleName === 'User').map((user) => (
                <MenuItem key={user.id} value={user.id}>
                  {user.username}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth>
            <InputLabel>Employee *</InputLabel>
            <Select
              value={currentIssue.employeeId}
              label="Employee *"
              onChange={(e) => setCurrentIssue({
                ...currentIssue,
                employeeId: e.target.value
              })}
            >
              {users.filter(u => u.role?.roleName === 'Admin' || u.role?.roleName === 'Employee').map((user) => (
                <MenuItem key={user.id} value={user.id}>
                  {user.username}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <DatePicker
            label="Issue Date"
            value={currentIssue.issueDate}
            onChange={(date) => setCurrentIssue({
              ...currentIssue,
              issueDate: date
            })}
            slotProps={{ textField: { fullWidth: true } }}
          />
          <DatePicker
            label="Return Date"
            value={currentIssue.returnDate}
            onChange={(date) => setCurrentIssue({
              ...currentIssue,
              returnDate: date
            })}
            slotProps={{ textField: { fullWidth: true } }}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={resetForm}>Cancel</Button>
          <Button
            onClick={isEditing ? handleUpdateIssue : handleAddIssue}
            color="primary"
            variant="contained"
            disabled={!currentIssue.bookId || !currentIssue.readerId || !currentIssue.employeeId}
          >
            {isEditing ? 'Update' : 'Save'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default IssuesPage;