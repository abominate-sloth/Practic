import { Box, Typography } from '@mui/material';

const HomePage = () => {
  return (
    <Box sx={{
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      height: '70vh',
      textAlign: 'center'
    }}>
      <Typography variant="h3" gutterBottom>
        Welcome to Library System
      </Typography>
      <Typography variant="h5">
        Use the navigation bar to manage genres
      </Typography>
    </Box>
  );
};

export default HomePage;