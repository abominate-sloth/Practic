import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import { getRoles, createRole, updateRole, deleteRole } from '../services/api';

const RolesPage = () => {
  const [roles, setRoles] = useState([]);
  const [newRoleName, setNewRoleName] = useState('');
  const [editingRole, setEditingRole] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  useEffect(() => {
    fetchRoles();
  }, []);

  const fetchRoles = async () => {
    try {
      const response = await getRoles();
      setRoles(response.data);
    } catch (error) {
      console.error('Error fetching roles:', error);
    }
  };

  const handleAddRole = async () => {
    if (!newRoleName.trim()) return;

    try {
      await createRole({ roleName: newRoleName });
      setNewRoleName('');
      fetchRoles();
    } catch (error) {
      console.error('Error adding role:', error);
    }
  };

  const handleUpdateRole = async () => {
    if (!editingRole?.roleName.trim()) return;

    try {
      await updateRole(editingRole.id, { roleName: editingRole.roleName });
      setEditingRole(null);
      setOpenDialog(false);
      fetchRoles();
    } catch (error) {
      console.error('Error updating role:', error);
    }
  };

  const handleDeleteRole = async (id) => {
    try {
      await deleteRole(id);
      fetchRoles();
    } catch (error) {
      console.error('Error deleting role:', error);
    }
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Roles</Typography>
        <Box display="flex" alignItems="center" gap={2}>
          <TextField
            size="small"
            label="New Role Name"
            value={newRoleName}
            onChange={(e) => setNewRoleName(e.target.value)}
          />
          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={handleAddRole}
          >
            Add Role
          </Button>
        </Box>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Role Name</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {roles.map((role) => (
              <TableRow key={role.id}>
                <TableCell>{role.id}</TableCell>
                <TableCell>{role.roleName}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setEditingRole(role);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteRole(role.id)}>
                    <Delete color="error" />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Edit Dialog */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} fullWidth maxWidth="sm">
        <DialogTitle>{editingRole ? 'Edit Role' : 'Add New Role'}</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="normal"
            label="Role Name"
            fullWidth
            value={editingRole?.roleName || ''}
            onChange={(e) => setEditingRole({
              ...editingRole,
              roleName: e.target.value
            })}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
          <Button onClick={handleUpdateRole} color="primary" variant="contained">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default RolesPage;