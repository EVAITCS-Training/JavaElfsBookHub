import * as yup from 'yup';
import { useFormik } from 'formik';
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Button,
  Rating,
  Grid,
  Container,
  Paper,
  Stepper,
  Step,
  StepLabel,
  Chip,
  Stack,
  Divider,
  FormHelperText
} from '@mui/material';
import {
  BookOutlined,
  PersonOutlined,
  CategoryOutlined,
  CalendarTodayOutlined,
  StarOutlineOutlined,
  SaveOutlined,
  ClearOutlined,
  PreviewOutlined
} from '@mui/icons-material';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';

export default function AddBook() {
    const queryClient = useQueryClient();
    const steps = ['Book Details', 'Review & Submit'];
    const activeStep = 0;

    const {mutate, isPending, error} = useMutation({
        mutationFn: async data => {
            const request = await axios.post(import.meta.env.VITE_API_URL + "/book/add", data, {
                headers: {
                    "Content-Type": "application/json"
                }
            })
            return request.data
        },
        onSuccess() {
            
        }
    })

    const genres = [
        'Fiction',
        'Non-Fiction',
        'Mystery',
        'Romance',
        'Science Fiction',
        'Fantasy',
        'Biography',
        'History',
        'Self-Help',
        'Business',
        'Psychology',
        'Philosophy',
        'Poetry',
        'Drama',
        'Horror',
        'Adventure',
        'Children\'s Books',
        'Young Adult',
        'Other'
    ];

    const popularBooks = [
        { title: 'Dune', author: 'Frank Herbert', rating: 5 },
        { title: '1984', author: 'George Orwell', rating: 4 },
        { title: 'The Hobbit', author: 'J.R.R. Tolkien', rating: 5 }
    ];
    const validationSchema = yup.object({
        title: yup.string().min(3, "Title has to be longer than 3 letters").max(150, "Title must shorten to a max of 150 characters").required("Title has to be filled out"),
        author: yup.string().min(3, "Author name has to be a min of 3 characters").max(300, "Author name can't be more than 300 characters").required("Author field has to be filled out"),
        genre: yup.string().min(6, "Genre has to meet the min character length of 6").max(30, "Genre can't be more than 30 characters long").required("Genre field required"),
        publishDate: yup.date().required("Publish Date is required"),
        rating: yup.number().min(1, "Rating has to 1 or more").max(5, "We have a 5 star rating system").required("Rating is required")
    })

    const formik = useFormik({
        initialValues: {
            title: "",
            author: "",
            genre: "",
            publishDate: "",
            rating: 1
        },
        validationSchema: validationSchema,
        onSubmit: (data) => {
            alert(JSON.stringify(data))
        }
    })
  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      {/* Hero Section */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #2c3e50, #34495e)',
          color: 'white',
          textAlign: 'center',
          py: 6,
          mb: 4,
          borderRadius: 2,
          position: 'relative',
          overflow: 'hidden'
        }}
      >
        <Typography variant="h3" component="h1" fontWeight="bold" gutterBottom>
          Add a New Book
        </Typography>
        <Typography variant="h6" sx={{ opacity: 0.9, mb: 3 }}>
          Share your latest read with the BookHub community
        </Typography>
        
        {/* Progress Stepper */}
        <Box sx={{ maxWidth: 400, mx: 'auto' }}>
          <Stepper activeStep={activeStep} alternativeLabel>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel sx={{ color: 'white' }}>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
        </Box>
      </Box>

      <Grid container spacing={4}>
        {/* Main Form */}
        <Grid xs={12} md={8} component="form" onSubmit={formik.handleSubmit}>
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
                Book Information
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Please fill in the details about the book you'd like to add
              </Typography>
            </Box>

            <CardContent sx={{ p: 4 }}>
              <Stack spacing={3}>
                {/* Title Field */}
                <TextField
                  fullWidth
                  name='title'
                  label="Book Title"
                  placeholder="Enter the book title (e.g., The Great Gatsby)"
                  required
                  value={formik.values.title}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  error={formik.touched.title && Boolean(formik.errors.title)}
                  InputProps={{
                    startAdornment: <BookOutlined sx={{ mr: 1, color: 'text.secondary' }} />
                  }}
                  helperText={formik.touched.title && formik.errors.title}
                />

                {/* Author Field */}
                <TextField
                  fullWidth
                  name='author'
                  label="Author"
                  placeholder="Enter author name (e.g., F. Scott Fitzgerald)"
                  required
                  value={formik.values.author}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  error={formik.touched.author && Boolean(formik.errors.author)}
                  InputProps={{
                    startAdornment: <PersonOutlined sx={{ mr: 1, color: 'text.secondary' }} />
                  }}
                  helperText={formik.touched.author && formik.errors.author}
                />

                {/* Genre Field */}
                <FormControl fullWidth required>
                  <InputLabel>Genre</InputLabel>
                  <Select
                    label="Genre"
                    name="genre"                    // ← ADD THIS!
                    value={formik.values.genre}     // ← ADD THIS!
                    onChange={formik.handleChange}  // ← ADD THIS!
                    onBlur={formik.handleBlur}
                    startAdornment={<CategoryOutlined sx={{ mr: 1, color: 'text.secondary' }} />}
                  >
                    {genres.map((genre) => (
                      <MenuItem key={genre} value={genre}>
                        {genre}
                      </MenuItem>
                    ))}
                  </Select>
                  {formik.touched.genre && formik.errors.genre && (  // ← ADD THIS!
                        <FormHelperText>{formik.errors.genre}</FormHelperText>
                    )}
                </FormControl>

                {/* Publication Date */}
                <TextField
                    fullWidth
                    name="publishDate"               // ← ADD THIS!
                    label="Publication Date"
                    type="date"
                    required
                    value={formik.values.publishDate}     // ← ADD THIS!
                    onChange={formik.handleChange}        // ← ADD THIS!
                    onBlur={formik.handleBlur}           // ← ADD THIS!
                    error={formik.touched.publishDate && Boolean(formik.errors.publishDate)}  // ← ADD THIS!
                    InputLabelProps={{
                        shrink: true,
                    }}
                    InputProps={{
                        startAdornment: <CalendarTodayOutlined sx={{ mr: 1, color: 'text.secondary' }} />
                    }}
                    helperText={formik.touched.publishDate && formik.errors.publishDate || "Select the original publication date of the book"}  // ← UPDATE THIS!
                    />

                {/* Rating Section */}
                <Box>
                    <Typography variant="body1" fontWeight="500" gutterBottom>
                        Your Rating <span style={{ color: '#d32f2f' }}>*</span>
                    </Typography>
                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 1 }}>
                        <StarOutlineOutlined sx={{ color: 'text.secondary' }} />
                        <Rating
                        name="rating"                           // ← ADD THIS!
                        size="large"
                        precision={1}
                        value={formik.values.rating}            // ← ADD THIS!
                        onChange={(event, newValue) => {        // ← ADD THIS!
                            formik.setFieldValue('rating', newValue || 1);
                        }}
                        onBlur={() => formik.setFieldTouched('rating', true)}  // ← ADD THIS!
                        emptyIcon={<StarOutlineOutlined fontSize="inherit" />}
                        />
                    </Box>
                    {formik.touched.rating && formik.errors.rating && (  // ← ADD THIS!
                        <Typography variant="caption" color="error" sx={{ display: 'block' }}>
                        {formik.errors.rating}
                        </Typography>
                    )}
                    <Stack direction="row" spacing={1} flexWrap="wrap" sx={{ mb: 1 }}>
                        <Chip label="1 = Poor" size="small" variant="outlined" />
                        <Chip label="2 = Fair" size="small" variant="outlined" />
                        <Chip label="3 = Good" size="small" variant="outlined" />
                        <Chip label="4 = Very Good" size="small" variant="outlined" />
                        <Chip label="5 = Excellent" size="small" variant="outlined" />
                    </Stack>
                    <Typography variant="caption" color="text.secondary" sx={{ display: 'block' }}>
                        Rate this book based on your personal reading experience
                    </Typography>
                    </Box>

                <Divider sx={{ my: 2 }} />

                {/* Action Buttons */}
                <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} justifyContent="flex-end">
                  <Button
                    variant="outlined"
                    color="secondary"
                    startIcon={<ClearOutlined />}
                    sx={{ minWidth: 120 }}
                  >
                    Cancel
                  </Button>
                  <Button
                    variant="outlined"
                    startIcon={<PreviewOutlined />}
                    sx={{ minWidth: 120 }}
                  >
                    Preview
                  </Button>
                  <Button
                    variant="contained"
                    type='submit'
                    endIcon={<SaveOutlined />}
                    sx={{ 
                      minWidth: 140,
                      background: 'linear-gradient(135deg, #3498db, #2980b9)',
                      '&:hover': {
                        background: 'linear-gradient(135deg, #2980b9, #1f5f8b)',
                      }
                    }}
                  >
                    Add Book
                  </Button>
                </Stack>

                <Box
                  sx={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    gap: 1,
                    mt: 2,
                    p: 2,
                    bgcolor: 'grey.50',
                    borderRadius: 1
                  }}
                >
                  <Typography variant="caption" color="text.secondary">
                    🔒 Your book information is secure and will be added to your personal library
                  </Typography>
                </Box>
              </Stack>
            </CardContent>
          </Card>
        </Grid>

        {/* Sidebar */}
        <Grid item xs={12} md={4}>
          <Stack spacing={3}>
            {/* Tips Card */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                💡 Tips for Adding Books
              </Typography>
              <Stack spacing={1.5}>
                <Typography variant="body2" color="text.secondary">
                  <strong>Title:</strong> Use the exact title from the book cover for best results
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Author:</strong> Include full author name as it appears on the book
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Genre:</strong> Choose the most appropriate primary genre
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Date:</strong> Use the original publication date, not reprint dates
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Rating:</strong> Rate based on your personal enjoyment
                </Typography>
              </Stack>
            </Paper>

            {/* Recently Added Books */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                📚 Recently Added
              </Typography>
              <Stack spacing={2}>
                {popularBooks.map((book, index) => (
                  <Box
                    key={index}
                    sx={{
                      display: 'flex',
                      alignItems: 'center',
                      gap: 2,
                      p: 1.5,
                      bgcolor: 'grey.50',
                      borderRadius: 1,
                      '&:hover': {
                        bgcolor: 'grey.100'
                      }
                    }}
                  >
                    <Box
                      sx={{
                        width: 40,
                        height: 40,
                        bgcolor: 'primary.main',
                        color: 'white',
                        borderRadius: 1,
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        fontSize: '1.2rem'
                      }}
                    >
                      📖
                    </Box>
                    <Box sx={{ flex: 1 }}>
                      <Typography variant="body2" fontWeight="600">
                        {book.title}
                      </Typography>
                      <Typography variant="caption" color="text.secondary">
                        {book.author}
                      </Typography>
                      <Box sx={{ mt: 0.5 }}>
                        <Rating size="small" value={book.rating} readOnly />
                      </Box>
                    </Box>
                  </Box>
                ))}
              </Stack>
            </Paper>

            {/* Help Card */}
            <Paper elevation={2} sx={{ p: 3, borderRadius: 2 }}>
              <Typography variant="h6" fontWeight="600" gutterBottom sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                ❓ Need Help?
              </Typography>
              <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                Can't find your book in our database?
              </Typography>
              <Stack direction="row" spacing={1} flexWrap="wrap">
                <Button size="small" variant="outlined">
                  Learn More
                </Button>
                <Button size="small" variant="text">
                  Contact Support
                </Button>
              </Stack>
            </Paper>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  )
}
