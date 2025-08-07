import { useState } from 'react';
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
  PersonAddOutlined
} from '@mui/icons-material';
import axios from 'axios';
import { useMutation } from '@tanstack/react-query';

interface RegisterFormData {
  email: string;
  password: string;
  confirmPassword: string;
  agreeToTerms: boolean;
}

interface RegisterRequest {
  email: string;
  password: string;
}

interface RegisterProps {
  onNavigate: (path: string) => void;
}

export default function Register({onNavigate}: RegisterProps) {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const registerPost = async (data: RegisterRequest): Promise<void> => {
    const response = await axios.post(import.meta.env.VITE_API_URL + "/auth/register", data, {
        headers: {
            "Content-Type": "application/json"
        }
    })

    if(response.status !== 201) {
        throw new Error("Registration failed")
    }
  }

  const {mutate, isPending} = useMutation({
    mutationFn: registerPost,
    onSuccess() {
        onNavigate("/login")
    },
    onError(error: Error) {
        console.error("Registration error:", error.message)
        alert("Registration failed: " + error.message)
    }
  })

  const validationSchema = yup.object({
    email: yup
      .string()
      .email('Please provide a valid email address')
      .required('Email is required'),
    password: yup
      .string()
      .min(8, 'Password must be at least 8 characters')
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])/,
        'Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character'
      )
      .required('Password is required'),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref('password'), undefined], 'Passwords must match')
      .required('Please confirm your password'),
    agreeToTerms: yup
      .boolean()
      .oneOf([true], 'You must agree to the terms and conditions')
  });

  const formik = useFormik<RegisterFormData>({
    initialValues: {
      email: '',
      password: '',
      confirmPassword: '',
      agreeToTerms: false
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      const registerData: RegisterRequest = {
        email: values.email,
        password: values.password
      };
      mutate(registerData);
    }
  });

  const communityStats = [
    { number: '2,500+', label: 'Happy Readers' },
    { number: '10,000+', label: 'Books Tracked' },
    { number: '15,000+', label: 'Reviews Written' }
  ];

  const benefits = [
    '📖 Track all your books in one place',
    '⭐ Rate and review your reads', 
    '📊 See your reading progress',
    '🎯 Set and achieve reading goals',
    '👥 Connect with fellow readers',
    '🔍 Discover new books through recommendations'
  ];

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
          borderRadius: 2,
          position: 'relative',
          overflow: 'hidden'
        }}
      >
        <Typography variant="h3" component="h1" fontWeight="bold" gutterBottom>
          Join BookHub
        </Typography>
        <Typography variant="h6" sx={{ opacity: 0.9, mb: 3 }}>
          Start tracking your reading journey today
        </Typography>
        <Box sx={{ fontSize: '4rem', mb: 2 }}>📚</Box>
      </Box>

      <Grid container spacing={4} justifyContent="center">
        {/* Registration Form */}
        <Grid size={{ xs: 12, md: 6 }}>
          <Card elevation={4} sx={{ borderRadius: 3 }}>
            <Box
              sx={{
                background: 'linear-gradient(135deg, #f8f9fa, #e9ecef)',
                p: 3,
                borderBottom: 1,
                borderColor: 'divider'
              }}
            >
              <Typography variant="h5" fontWeight="600" color="primary" gutterBottom>
                Create Your Account
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Enter your email and password to get started
              </Typography>
            </Box>

            <CardContent sx={{ p: 4 }}>
              <form onSubmit={formik.handleSubmit}>
                <Stack spacing={3}>
                  {/* Status Messages */}
                  {formik.status && (
                    <Alert 
                      severity={formik.status.type}
                      sx={{ mb: 2 }}
                    >
                      {formik.status.message}
                    </Alert>
                  )}

                  {/* Email Field */}
                  <TextField
                    fullWidth
                    name="email"
                    label="Email Address"
                    placeholder="Enter your email address"
                    type="email"
                    value={formik.values.email}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    error={formik.touched.email && Boolean(formik.errors.email)}
                    helperText={formik.touched.email && formik.errors.email || "We'll use this to send you login information"}
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
                    placeholder="Create a password"
                    type={showPassword ? 'text' : 'password'}
                    value={formik.values.password}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    error={formik.touched.password && Boolean(formik.errors.password)}
                    helperText={formik.touched.password && formik.errors.password || "At least 8 characters with uppercase, lowercase, number and special character"}
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
                            size="small"
                          >
                            {showPassword ? <VisibilityOffOutlined /> : <VisibilityOutlined />}
                          </IconButton>
                        </InputAdornment>
                      )
                    }}
                  />

                  {/* Confirm Password Field */}
                  <TextField
                    fullWidth
                    name="confirmPassword"
                    label="Confirm Password"
                    placeholder="Confirm your password"
                    type={showConfirmPassword ? 'text' : 'password'}
                    value={formik.values.confirmPassword}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    error={formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)}
                    helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
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
                            onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                            edge="end"
                            size="small"
                          >
                            {showConfirmPassword ? <VisibilityOffOutlined /> : <VisibilityOutlined />}
                          </IconButton>
                        </InputAdornment>
                      )
                    }}
                  />

                  {/* Terms and Conditions */}
                  <FormControlLabel
                    control={
                      <Checkbox
                        name="agreeToTerms"
                        checked={formik.values.agreeToTerms}
                        onChange={formik.handleChange}
                        color="primary"
                      />
                    }
                    label={
                      <Typography variant="body2" color="text.secondary">
                        I agree to the{' '}
                        <Link href="/terms" target="_blank" color="primary">
                          Terms of Service
                        </Link>{' '}
                        and{' '}
                        <Link href="/privacy" target="_blank" color="primary">
                          Privacy Policy
                        </Link>
                      </Typography>
                    }
                  />
                  {formik.touched.agreeToTerms && formik.errors.agreeToTerms && (
                    <Typography variant="caption" color="error">
                      {formik.errors.agreeToTerms}
                    </Typography>
                  )}

                  <Divider sx={{ my: 2 }} />

                  {/* Submit Button */}
                  <Button
                    type="submit"
                    variant="contained"
                    size="large"
                    fullWidth
                    disabled={isPending} 
                    endIcon={<PersonAddOutlined />}
                    sx={{
                      py: 1.5,
                      background: 'linear-gradient(135deg, #3498db, #2980b9)',
                      '&:hover': {
                        background: 'linear-gradient(135deg, #2980b9, #1f5f8b)',
                      }
                    }}
                  >
                    {isPending ? 'Creating Account...' : 'Create Account'}
                  </Button>

                  {/* Login Link */}
                  <Box sx={{ textAlign: 'center', mt: 2 }}>
                    <Typography variant="body2" color="text.secondary">
                      Already have an account?{' '}
                      <Link href="/login" color="primary" sx={{ fontWeight: 500 }}>
                        Sign in here
                      </Link>
                    </Typography>
                  </Box>
                </Stack>
              </form>
            </CardContent>
          </Card>
        </Grid>

        {/* Benefits Sidebar */}
        <Grid size={{ xs: 12, md: 4 }}>
          <Stack spacing={3}>
            {/* Welcome Card */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                📚 Welcome to BookHub
              </Typography>
              <Stack spacing={1.5}>
                {benefits.map((benefit, index) => (
                  <Typography key={index} variant="body2" color="text.secondary">
                    {benefit}
                  </Typography>
                ))}
              </Stack>
            </Paper>

            {/* Community Stats */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                👥 Join Our Community
              </Typography>
              <Grid container spacing={2} sx={{ mt: 1 }}>
                {communityStats.map((stat, index) => (
                  <Grid size={{ xs: 12 }} key={index}>
                    <Box sx={{ textAlign: 'center', py: 1 }}>
                      <Typography variant="h4" fontWeight="bold" color="primary">
                        {stat.number}
                      </Typography>
                      <Typography variant="caption" color="text.secondary">
                        {stat.label}
                      </Typography>
                    </Box>
                  </Grid>
                ))}
              </Grid>
            </Paper>

            {/* Security Note */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2, bgcolor: 'success.50' }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                🔒 Your Privacy Matters
              </Typography>
              <Typography variant="body2" color="text.secondary">
                We use industry-standard encryption to protect your data. Your information is never shared with third parties without your consent.
              </Typography>
            </Paper>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}