import React, { useState } from 'react';
import { useFormik } from 'formik';
import * as yup from 'yup';
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Grid,
  Container,
  Paper,
  Stack,
  Divider,
  IconButton,
  InputAdornment,
  Alert,
  Checkbox,
  FormControlLabel,
  Link
} from '@mui/material';
import {
  EmailOutlined,
  LockOutlined,
  VisibilityOutlined,
  VisibilityOffOutlined,
  LoginOutlined
} from '@mui/icons-material';
import axios from 'axios';
import { useMutation } from '@tanstack/react-query';

interface LoginProps {
  onNavigate: (path: string) => void;
}

export default function Login({onNavigate}: LoginProps) {
  const [showPassword, setShowPassword] = useState(false);

  const loginPost = async (data) => {
    const response = await axios.post(import.meta.env.VITE_API_URL + "/auth/login", data, {
        headers: {
            "Content-Type": "application/json"
        }
    })

    if(response.status !== 200) {
        throw new Error("Invalid Credentials")
    }

    return response.data
  }

  const {mutate} = useMutation({
    mutationFn: loginPost,
    onSuccess(data) {
        sessionStorage.setItem("Authorization", data.token)
        onNavigate("/books")
    },
    onError(error) {
        alert(error.message)
    }
  })

  // Validation schema matching your AuthRequest DTO
  const validationSchema = yup.object({
    username: yup
      .string()
      .email('Please provide a valid email address')
      .required('Email is required'),
    password: yup
      .string()
      .min(6, 'Password must be at least 6 characters')
      .required('Password is required')
  });

  const formik = useFormik({
    initialValues: {
      username: '', // Maps to email in your system
      password: ''
    },
    validationSchema: validationSchema,
    onSubmit: mutate 
  });

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      {/* Hero Section */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #2c3e50, #34495e)',
          color: 'white',
          textAlign: 'center',
          py: 8,
          mb: 4,
          borderRadius: 2
        }}
      >
        <Typography variant="h3" component="h1" fontWeight="bold" gutterBottom>
          Welcome Back
        </Typography>
        <Typography variant="h6" sx={{ opacity: 0.9, mb: 3 }}>
          Sign in to continue your reading journey
        </Typography>
        <Box sx={{ fontSize: '4rem', mb: 2 }}>📚</Box>
      </Box>

      <Grid container spacing={4} justifyContent="center">
        {/* Login Form */}
        <Grid item xs={12} md={6}>
          <Card elevation={4} sx={{ borderRadius: 3 }}>
            <Box
              sx={{
                background: 'linear-gradient(135deg, #f8f9fa, #e9ecef)',
                p: 3,
                borderBottom: 1,
                borderColor: 'divider'
              }}
            >
              <Typography variant="h5" fontWeight="600" color="primary">
                Sign In
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Enter your credentials to access your account
              </Typography>
            </Box>

            <CardContent sx={{ p: 4 }}>
              <Stack spacing={3} component="form" onSubmit={formik.handleSubmit}>
                
                {/* Status Messages */}
                {formik.status && (
                  <Alert severity={formik.status.type}>
                    {formik.status.message}
                  </Alert>
                )}

                {/* Email/Username Field */}
                <TextField
                  fullWidth
                  name="username"
                  label="Email Address"
                  placeholder="Enter your email address"
                  type="email"
                  value={formik.values.username}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  error={formik.touched.username && Boolean(formik.errors.username)}
                  helperText={formik.touched.username && formik.errors.username}
                  required
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <EmailOutlined sx={{ color: 'text.secondary' }} />
                      </InputAdornment>
                    )
                  }}
                />

                {/* Password Field */}
                <TextField
                  fullWidth
                  name="password"
                  label="Password"
                  placeholder="Enter your password"
                  type={showPassword ? 'text' : 'password'}
                  value={formik.values.password}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  error={formik.touched.password && Boolean(formik.errors.password)}
                  helperText={formik.touched.password && formik.errors.password}
                  required
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <LockOutlined sx={{ color: 'text.secondary' }} />
                      </InputAdornment>
                    ),
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          onClick={() => setShowPassword(!showPassword)}
                          edge="end"
                        >
                          {showPassword ? <VisibilityOffOutlined /> : <VisibilityOutlined />}
                        </IconButton>
                      </InputAdornment>
                    )
                  }}
                />

                {/* Remember Me & Forgot Password */}
                <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                  <FormControlLabel
                    control={<Checkbox color="primary" />}
                    label={
                      <Typography variant="body2" color="text.secondary">
                        Remember me
                      </Typography>
                    }
                  />
                  <Link href="/forgot-password" variant="body2" color="primary">
                    Forgot password?
                  </Link>
                </Box>

                {/* Submit Button */}
                <Button
                  type="submit"
                  variant="contained"
                  size="large"
                  fullWidth
                  disabled={formik.isSubmitting}
                  endIcon={<LoginOutlined />}
                  sx={{
                    py: 1.5,
                    background: 'linear-gradient(135deg, #3498db, #2980b9)',
                    '&:hover': {
                      background: 'linear-gradient(135deg, #2980b9, #1f5f8b)',
                    }
                  }}
                >
                  {formik.isSubmitting ? 'Signing In...' : 'Sign In'}
                </Button>

                <Divider>or</Divider>

                {/* Register Link */}
                <Box sx={{ textAlign: 'center' }}>
                  <Typography variant="body2" color="text.secondary">
                    Don't have an account?{' '}
                    <Link href="/register" color="primary">
                      Create one here
                    </Link>
                  </Typography>
                </Box>
              </Stack>
            </CardContent>
          </Card>
        </Grid>

        {/* Info Sidebar */}
        <Grid item xs={12} md={4}>
          <Stack spacing={3}>
            {/* Welcome Back */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom>
                👋 Welcome Back!
              </Typography>
              <Stack spacing={1.5}>
                <Typography variant="body2" color="text.secondary">
                  📖 Continue tracking your reading progress
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  ⭐ Check out new reviews from the community
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  🎯 Update your reading goals for the year
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  📊 View your reading statistics
                </Typography>
              </Stack>
            </Paper>

            {/* Security */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2, bgcolor: 'success.50' }}>
              <Typography variant="h6" fontWeight="600" gutterBottom>
                🔐 Secure Login
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Your login is protected with JWT authentication and industry-standard encryption.
              </Typography>
            </Paper>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}