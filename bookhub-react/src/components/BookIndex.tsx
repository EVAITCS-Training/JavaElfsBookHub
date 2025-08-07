// bookhub-react/src/components/BookIndex.tsx
import { ErrorOutline, Refresh, Star, StarBorder } from "@mui/icons-material";
import { Box, Button, CircularProgress, Typography } from "@mui/material";
import { DataGrid, type GridColDef } from "@mui/x-data-grid"
import { useQuery } from "@tanstack/react-query";
import axios from 'axios'

const columns: GridColDef[] = [
    {
        field: 'title',
        headerName: 'Title',
        width: 150,
        renderCell: (params) => (
            <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                {params.value || 'N/A'}
            </Typography>
        )
    },
    {
        field: 'author',
        headerName: 'Author',
        width: 150,
        renderCell: (params) => (
            <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                {params.value || 'N/A'}
            </Typography>
        )
    },
    {
        field: 'genre',
        headerName: 'Genre',
        width: 150,
        renderCell: (params) => (
            <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                {params.value || 'N/A'}
            </Typography>
        )
    },
    {
        field: 'rating',
        headerName: 'Rating',
        width: 150,
        renderCell: (params) => {
            const rating = params.value || 0;
            return (
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 0.25 }}>
                    {Array.from({ length: 5 }, (_, index) => (
                        index < rating ? (
                            <Star key={index} sx={{ color: '#f1c40f', fontSize: '1.2rem' }} />
                        ) : (
                            <StarBorder key={index} sx={{ color: '#ddd', fontSize: '1.2rem' }} />
                        )
                    ))}
                </Box>
            );
        }
    }
];

export default function BookIndex() {
    const { data, isLoading, error, refetch } = useQuery({
        queryKey: ['books'],
        queryFn: async () => {
            const response = await axios.get(import.meta.env.VITE_API_URL + "/book/")
            if(response.status !== 200) {
                throw new Error("Error retrieving books")
            }
            return response.data
        }
    })

     if (isLoading) {
        return(
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                    bgcolor: 'background.default',
                    gap: 3
                }}
            >
                <CircularProgress 
                    size={60} 
                    thickness={4}
                    sx={{ color: 'primary.main' }}
                />
                <Typography variant="h6" color="text.secondary">
                    Loading your books table...
                </Typography>
            </Box>
        )
    }

    if (error) {
        return (
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                    bgcolor: 'background.default',
                    p: 3
                }}
            >
                <ErrorOutline sx={{ fontSize: 48, color: 'error.main', mb: 2 }} />
                
                <Typography variant="h5" gutterBottom>
                    Unable to load books table
                </Typography>
                
                <Typography variant="body1" color="text.secondary" sx={{ mb: 3 }}>
                    Please try refreshing the page
                </Typography>
                
                <Button
                    variant="contained"
                    startIcon={<Refresh />}
                    onClick={() => refetch()}
                >
                    Try Again
                </Button>
            </Box>
        )
    }

  return (
    <>
        <div>Bookhub Library</div>
        <DataGrid
            rows={data || []}
            columns={columns}
            loading={isLoading}
        />
    </>
  )
}