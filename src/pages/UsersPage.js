import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions,
  MenuItem, Select, InputLabel, FormControl
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import { DatePicker } from '@mui/x-date-pickers';
import { getUsers, createUser, updateUser, deleteUser, getRoles } from '../services/api';
import dayjs from 'dayjs';

const UsersPage = () => {
  const [users, setUsers] = useState([]);
  const [roles, setRoles] = useState([]);
  const [currentUser, setCurrentUser] = useState({
    username: '',
    email: '',
    joinDate: null,
    roleId: ''
  });
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchUsers();
    fetchRoles();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await getUsers();
      setUsers(response.data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  const fetchRoles = async () => {
    try {
      const response = await getRoles();
      setRoles(response.data);
    } catch (error) {
      console.error('Error fetching roles:', error);
    }
  };

  const handleAddUser = async () => {
    if (!currentUser.username.trim() || !currentUser.roleId) return;

    try {
      const userData = {
        username: currentUser.username,
        passwordHash: 'defaultPassword',
        email: currentUser.email,
        joinDate: currentUser.joinDate ? currentUser.joinDate.format('YYYY-MM-DD') : null,
        roleId: parseInt(currentUser.roleId)
      };
      await createUser(userData);
      resetForm();
      fetchUsers();
    } catch (error) {
      console.error('Error adding user:', error);
    }
  };

  const handleUpdateUser = async () => {
    if (!currentUser?.username.trim() || !currentUser?.roleId) return;

    try {
      const userData = {
        username: currentUser.username,
        email: currentUser.email,
        passwordHash: "defaultPassword",
        joinDate: currentUser.joinDate ? currentUser.joinDate.format('YYYY-MM-DD') : null,
        roleId: parseInt(currentUser.roleId)
      };
      await updateUser(currentUser.id, userData);
      resetForm();
      fetchUsers();
    } catch (error) {
      console.error('Error updating user:', error);
    }
  };

  const handleDeleteUser = async (id) => {
    try {
      await deleteUser(id);
      fetchUsers();
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  const resetForm = () => {
    setCurrentUser({
      username: '',
      email: '',
      joinDate: null,
      roleId: ''
    });
    setOpenDialog(false);
    setIsEditing(false);
  };

  const formatDate = (dateString) => {
    if (!dateString) return '-';
    return dayjs(dateString).format('DD.MM.YYYY');
  };

  const getRoleNameById = (roleId) => {
    const role = roles.find(r => r.id === roleId);
    return role ? role.roleName : '-';
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Users</Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
          onClick={() => {
            setCurrentUser({
              username: '',
              email: '',
              joinDate: null,
              roleId: ''
            });
            setIsEditing(false);
            setOpenDialog(true);
          }}
        >
          Add User
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Username</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Join Date</TableCell>
              <TableCell>Role</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users.map((user) => (
              <TableRow key={user.id}>
                <TableCell>{user.id}</TableCell>
                <TableCell>{user.username}</TableCell>
                <TableCell>{user.email || '-'}</TableCell>
                <TableCell>{formatDate(user.joinDate)}</TableCell>
                <TableCell>{getRoleNameById(user.role?.id)}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setCurrentUser({
                      ...user,
                      joinDate: user.joinDate ? dayjs(user.joinDate) : null,
                      roleId: user.role?.id || ''
                    });
                    setIsEditing(true);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteUser(user.id)}>
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
        <DialogTitle>{isEditing ? 'Edit User' : 'Add New User'}</DialogTitle>
        <DialogContent sx={{ pt: 2, '& > *': { my: 1 } }}>
          <TextField
            autoFocus
            fullWidth
            label="Username *"
            value={currentUser.username}
            onChange={(e) => setCurrentUser({
              ...currentUser,
              username: e.target.value
            })}
          />
          <TextField
            fullWidth
            label="Email"
            type="email"
            value={currentUser.email}
            onChange={(e) => setCurrentUser({
              ...currentUser,
              email: e.target.value
            })}
          />
          <DatePicker
            label="Join Date"
            value={currentUser.joinDate}
            onChange={(date) => setCurrentUser({
              ...currentUser,
              joinDate: date
            })}
            slotProps={{ textField: { fullWidth: true } }}
          />
          <FormControl fullWidth>
            <InputLabel>Role *</InputLabel>
            <Select
              value={currentUser.roleId}
              label="Role *"
              onChange={(e) => setCurrentUser({
                ...currentUser,
                roleId: e.target.value
              })}
            >
              {roles.map((role) => (
                <MenuItem key={role.id} value={role.id}>
                  {role.roleName}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </DialogContent>
        <DialogActions>
          <Button onClick={resetForm}>Cancel</Button>
          <Button
            onClick={isEditing ? handleUpdateUser : handleAddUser}
            color="primary"
            variant="contained"
            disabled={!currentUser.username || !currentUser.roleId}
          >
            {isEditing ? 'Update' : 'Save'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default UsersPage;