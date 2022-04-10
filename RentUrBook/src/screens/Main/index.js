import React, { useState, useEffect } from "react";

import { View} from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { BottomNavigation } from "react-native-paper";

import Home from "../Home"
import TabBar, { iconTypes } from "react-native-fluidbottomnavigation";
import Favorite from "../Favorite"
import Notifications from "../Notifications"
import Account from "../Account"


const Main = ({navigation, route}) => {
    console.log("params",route.params)
    const [userData, setUserData] = useState(route.params);

    console.log("Main data ", userData);


    const [tabBarIndex, setTabIndex] = useState(0);
    const [tabBarRoute, setTabRoute] = useState (
        [
            {
              key: 'home',
              title: 'Home',
            },
            {
              key: 'favorite',
              title: 'Favorite',
            },
            {
              key: 'notifications',
              title: 'Notifications',
            },
            {
              key: 'account',
              title: 'Account',
            },
          ]
    );

    const _handleIndexChange = (index) => {
        setTabIndex(index) 

    }

    const _tabBarPress = (tabIndex) => {
        _handleIndexChange(tabIndex)
    }
    const _renderTabScene = BottomNavigation.SceneMap({
        home: () => <Home userData={userData} navigation={navigation}/>,
        favorite: () => <Favorite navigation={navigation} userData={userData} />,
        notifications: () => <Notifications userData={userData} />,
        account: () => <Account navigation={navigation} userData={userData}/>
    })

    return(
        <View style={{flex:1}}>
            <BottomNavigation
            navigationState={{index: tabBarIndex, routes: tabBarRoute}}
            onIndexChange={_handleIndexChange}
            renderScene={_renderTabScene}
            barStyle={{
                display: 'none',
            }}
            />
            <TabBar
                activeTab={tabBarIndex}
                onPress={(tabIndex) => { 
                    _tabBarPress(tabIndex)
                }}
                iconStyle={{ width: 50, height: 50 }}
                iconActiveTintColor="white"
                iconInactiveTintColor="#CCCCCC"
                tintColor="#6C70EB"
                titleColor="#6C70EB" 
                values={[
                    { title: "Home", icon: "home", isIcon: true, iconType: iconTypes.Entypo },
                    { title: "Favorites", icon: "heart", isIcon: true, iconType: iconTypes.FontAwesome },
                    { title: "Notification", icon: "notifications", isIcon: true, iconType: iconTypes.MaterialIcons },
                    { title: "Account", icon: "user-alt", isIcon: true, iconType: iconTypes.FontAwesome5 }
                ]}
            />
        </View>
    )
}

export default Main;