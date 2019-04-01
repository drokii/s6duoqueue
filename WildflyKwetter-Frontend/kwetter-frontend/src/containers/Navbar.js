import React from 'react'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import LoginDialog from '../components/LoginDialog';
import { withRouter } from 'react-router-dom'

const NavBar = withRouter(({ history }) => {
    return (
        <div style={{ flexGrow: 1 }} >
            <AppBar position="static">
                <Toolbar>
                    <Typography onClick={() => { history.push('/') }} style={{ flexGrow: 1 }} variant="title" color="inherit" align="left">
                        Kwetter
                </Typography>
                    <LoginDialog />
                </Toolbar>
            </AppBar>
        </div>
    )
})
export default NavBar;